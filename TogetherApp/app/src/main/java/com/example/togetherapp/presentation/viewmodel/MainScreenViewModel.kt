package com.example.togetherapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.togetherapp.domain.usecase.course.GetCoursesUseCase
import com.example.togetherapp.domain.usecase.comnote.GetNotesUseCase
import com.example.togetherapp.domain.usecase.locnote.GetAllLocalNotesUseCase
import com.example.togetherapp.presentation.event.MainScreenEvent
import com.example.togetherapp.presentation.state.MainScreenState
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val getCoursesUseCase: GetCoursesUseCase,
    private val getNotesUseCase: GetNotesUseCase,
    private val getAllLocalNotesUseCase: GetAllLocalNotesUseCase
) : ViewModel() {

    private val _state = MutableLiveData(MainScreenState())
    val state: LiveData<MainScreenState> get() = _state

    fun handleEvent(event: MainScreenEvent) {
        when (event) {
            is MainScreenEvent.LoadCourses -> {
                fetchCourses()
            }

            is MainScreenEvent.LoadNotes -> {
                fetchNotes()
            }

            is MainScreenEvent.LoadLocalNotes -> {
                fetchLocalNotes()
            }

            is MainScreenEvent.ShowAllCourses -> {
                _state.value = _state.value?.copy(showAllCourses = true)
                loadCourses()
            }

            is MainScreenEvent.HideAllCourses -> {
                _state.value = _state.value?.copy(showAllCourses = false)
            }

            is MainScreenEvent.ShowAllNotes -> {
                _state.value = _state.value?.copy(showAllNotes = true)
                loadNotes()
            }

            is MainScreenEvent.ShowAllLocalNotes -> {
                _state.value = _state.value?.copy(showAllLocalNotes = true)
                loadLocalNotes()
            }

            is MainScreenEvent.HideAllLocalNotes -> {
                _state.value = _state.value?.copy(showAllLocalNotes = false)
            }

            is MainScreenEvent.HideAllNotes -> {
                _state.value = _state.value?.copy(showAllNotes = false)
            }

            is MainScreenEvent.NavigateToLogin -> {
                _state.value = _state.value?.copy(isNavigatedToLogin = true)
            }
        }
    }

    private fun loadLocalNotes() {
        _state.value = _state.value?.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val notes = getAllLocalNotesUseCase.execute()
                _state.value = _state.value?.copy(localNotes = notes, isLoading = false)
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message, isLoading = false)
            }
        }
    }

    private fun fetchLocalNotes() {
        viewModelScope.launch {
            _state.value = _state.value?.copy(isLoading = true)
            try {
                val notesList = getAllLocalNotesUseCase.execute()
                val lastLocalNote = notesList.firstOrNull()
                _state.value = _state.value?.copy(localNote = lastLocalNote)
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message)
            } finally {
                _state.value = _state.value?.copy(isLoading = false)
            }
        }
    }

    private fun loadNotes() {
        _state.value = _state.value?.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val notes = getNotesUseCase.execute()
                _state.value = _state.value?.copy(notes = notes, isLoading = false)
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message, isLoading = false)
            }
        }
    }

    private fun loadCourses() {
        _state.value = _state.value?.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val courses = getCoursesUseCase.execute()
                _state.value = _state.value?.copy(courses = courses, isLoading = false)
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message, isLoading = false)
            }
        }
    }

    private fun fetchCourses() {
        viewModelScope.launch {
            _state.value = _state.value?.copy(isLoading = true)
            try {
                val coursesList = getCoursesUseCase.execute()
                _state.value = _state.value?.copy(courses = coursesList.take(6))
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message)
            } finally {
                _state.value = _state.value?.copy(isLoading = false)
            }
        }
    }

    private fun fetchNotes() {
        viewModelScope.launch {
            _state.value = _state.value?.copy(isLoading = true)
            try {
                val notesList = getNotesUseCase.execute()
                val lastCommunityNote = notesList.firstOrNull()
                _state.value = _state.value?.copy(communityNote = lastCommunityNote)
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message)
            } finally {
                _state.value = _state.value?.copy(isLoading = false)
            }
        }
    }
}
