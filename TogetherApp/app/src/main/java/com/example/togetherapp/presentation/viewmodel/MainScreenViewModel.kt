package com.example.togetherapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.togetherapp.domain.usecase.course.GetCoursesUseCase
import com.example.togetherapp.domain.usecase.note.GetNotesUseCase
import com.example.togetherapp.presentation.event.MainScreenEvent
import com.example.togetherapp.presentation.state.MainScreenState
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val getCoursesUseCase: GetCoursesUseCase,
    private val getNotesUseCase: GetNotesUseCase
) : ViewModel() {

    private val _state = MutableLiveData(MainScreenState())
    val state: LiveData<MainScreenState> get() = _state

    fun handleEvent(event: MainScreenEvent) {
        when (event) {
            is MainScreenEvent.LoadCourses -> fetchCourses()
            is MainScreenEvent.LoadNotes -> fetchNotes()
            is MainScreenEvent.ShowAllCourses -> {
                _state.value = _state.value?.copy(showAllCourses = true)
                loadCourses()
            }
            is MainScreenEvent.HideAllCourses -> {
                _state.value = _state.value?.copy(showAllCourses = false)
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
                val lastCommunityNote = notesList.lastOrNull()
                _state.value = _state.value?.copy(communityNote = lastCommunityNote)
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message)
            } finally {
                _state.value = _state.value?.copy(isLoading = false)
            }
        }
    }
}


//private fun fetchCoursesAndNotes() {
//    viewModelScope.launch {
//        _state.value = MainScreenState(isLoading = true)
//        try {
//            val coursesList = getCoursesUseCase.execute()
//            val notesList = getNotesUseCase.execute()
//            val lastCommunityNote = notesList.lastOrNull()
//
//            _state.value = MainScreenState(
//                courses = coursesList.take(6),
//                communityNote = lastCommunityNote
//            )
//        } catch (e: Exception) {
//            _state.value = MainScreenState(error = e.message)
//        } finally {
//            _state.value = _state.value?.copy(isLoading = false)
//        }
//    }
//}