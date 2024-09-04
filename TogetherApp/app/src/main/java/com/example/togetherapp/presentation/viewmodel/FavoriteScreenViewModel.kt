package com.example.togetherapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.togetherapp.domain.usecase.favorite.GetAllFavoriteComNotesUseCase
import com.example.togetherapp.domain.usecase.favorite.GetAllFavoriteCoursesUseCase
import com.example.togetherapp.domain.usecase.favorite.GetAllFavoriteLocalNotesUseCase
import com.example.togetherapp.presentation.event.CNoteDetailsScreenEvent
import com.example.togetherapp.presentation.event.CourseDetailsScreenEvent
import com.example.togetherapp.presentation.event.CreateNoteScreenEvent
import com.example.togetherapp.presentation.event.FavoriteScreenEvent
import com.example.togetherapp.presentation.event.LNoteDetailsScreenEvent
import com.example.togetherapp.presentation.event.MainScreenEvent
import com.example.togetherapp.presentation.event.ProfileScreenEvent
import com.example.togetherapp.presentation.state.FavoriteScreenState
import kotlinx.coroutines.launch

class FavoriteScreenViewModel(
    private val getAllFavoriteCoursesUseCase: GetAllFavoriteCoursesUseCase,
    private val getAllFavoriteComNotesUseCase: GetAllFavoriteComNotesUseCase,
    private val getAllFavoriteLocalNotesUseCase: GetAllFavoriteLocalNotesUseCase
) : ViewModel() {

    private val _state = MutableLiveData(FavoriteScreenState())
    val state: LiveData<FavoriteScreenState> get() = _state

    fun handleEvent(event: FavoriteScreenEvent) {
        when (event) {
            is FavoriteScreenEvent.OnErrorClear -> {
                _state.value = _state.value?.copy(error = null)
            }

            is FavoriteScreenEvent.HideAllCourses -> {
                _state.value = _state.value?.copy(showAllCourses = false)
            }

            is FavoriteScreenEvent.HideAllLocalNotes -> {
                _state.value = _state.value?.copy(showAllLocalNotes = false)
            }

            is FavoriteScreenEvent.HideAllNotes -> {
                _state.value = _state.value?.copy(showAllNotes = false)
            }

            is FavoriteScreenEvent.LoadCourses -> {
                fetchCourses()
            }

            is FavoriteScreenEvent.LoadLocalNotes -> {
                fetchLocalNotes()
            }

            is FavoriteScreenEvent.LoadNotes -> {
                fetchNotes()
            }

            is FavoriteScreenEvent.ShowAllCourses -> {
                _state.value = _state.value?.copy(showAllCourses = true)
                loadCourses()
            }

            is FavoriteScreenEvent.ShowAllLocalNotes -> {
                _state.value = _state.value?.copy(showAllLocalNotes = true)
                loadLocalNotes()
            }

            is FavoriteScreenEvent.ShowAllNotes -> {
                _state.value = _state.value?.copy(showAllNotes = true)
                loadNotes()
            }
        }
    }


    private fun loadNotes() {
        _state.value = _state.value?.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val notes = getAllFavoriteComNotesUseCase.execute()
                _state.value = _state.value?.copy(notes = notes, isLoading = false)
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message, isLoading = false)
            }
        }
    }

    private fun loadLocalNotes() {
        _state.value = _state.value?.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val notes = getAllFavoriteLocalNotesUseCase.execute()
                _state.value = _state.value?.copy(localNotes = notes, isLoading = false)
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message, isLoading = false)
            }
        }
    }

    private fun loadCourses() {
        _state.value = _state.value?.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val courses = getAllFavoriteCoursesUseCase.execute()
                _state.value = _state.value?.copy(courses = courses, isLoading = false)
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message, isLoading = false)
            }
        }
    }

    private fun fetchNotes() {
        viewModelScope.launch {
            _state.value = _state.value?.copy(isLoading = true)
            try {
                val notesList = getAllFavoriteComNotesUseCase.execute()
                val lastCommunityNote = notesList.firstOrNull()
                _state.value =
                    _state.value?.copy(communityNote = lastCommunityNote, isLoading = false)
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message)
            } finally {
                _state.value = _state.value?.copy(isLoading = false)
            }
        }
    }

    private fun fetchLocalNotes() {
        viewModelScope.launch {
            _state.value = _state.value?.copy(isLoading = true)
            try {
                val notesList = getAllFavoriteLocalNotesUseCase.execute()
                val lastLocalNote = notesList.firstOrNull()
                _state.value = _state.value?.copy(localNote = lastLocalNote)
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message)
            } finally {
                _state.value = _state.value?.copy(isLoading = false)
            }
        }
    }

    private fun fetchCourses() {
        viewModelScope.launch {
            _state.value = _state.value?.copy(isLoading = true)
            try {
                val coursesList = getAllFavoriteCoursesUseCase.execute()
                _state.value = _state.value?.copy(courses = coursesList.take(6))
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message)
            } finally {
                _state.value = _state.value?.copy(isLoading = false)
            }
        }
    }
}