package com.example.togetherapp.presentation.viewmodel.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.togetherapp.domain.usecase.course.GetCourseByIdUseCase
import com.example.togetherapp.domain.usecase.favorite.AddFavoriteCourseUseCase
import com.example.togetherapp.domain.usecase.favorite.CheckCourseFavoriteStatusUseCase
import com.example.togetherapp.domain.usecase.favorite.RemoveFavoriteCourseUseCase
import com.example.togetherapp.presentation.event.CourseDetailsScreenEvent
import com.example.togetherapp.presentation.state.CourseDetailsScreenState
import kotlinx.coroutines.launch

class CourseDetailsScreenViewModel(
    private val getCourseDetailsUseCase: GetCourseByIdUseCase,
    private val addFavoriteCourseUseCase: AddFavoriteCourseUseCase,
    private val removeFavoriteCourseUseCase: RemoveFavoriteCourseUseCase,
    private val checkCourseFavoriteStatusUseCase: CheckCourseFavoriteStatusUseCase
) : ViewModel() {

    private val _state = MutableLiveData(CourseDetailsScreenState())
    val state: LiveData<CourseDetailsScreenState> get() = _state

    fun handleEvent(event: CourseDetailsScreenEvent) {
        when (event) {
            is CourseDetailsScreenEvent.OnErrorClear -> {
                _state.value = _state.value?.copy(error = null)
            }

            is CourseDetailsScreenEvent.LoadCourseDetails -> {
                loadCourseDetails(event.courseId)
            }

            is CourseDetailsScreenEvent.AddToFavorite -> {
                addToFavorite()
            }

            is CourseDetailsScreenEvent.RemoveFromFavorite -> {
                removeFromFavorite()
            }

            is CourseDetailsScreenEvent.CheckIfFavorite -> {
                checkIfFavorite(event.courseId)
            }
        }
    }

    private fun checkIfFavorite(courseId: String) {
        viewModelScope.launch {
            try {
                val isFavorite = checkCourseFavoriteStatusUseCase.execute(courseId)
                _state.value = _state.value?.copy(isFavorite = isFavorite)
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message)
            }
        }
    }

    private fun removeFromFavorite() {
        viewModelScope.launch {
            try {
                state.value?.course?.let { removeFavoriteCourseUseCase.execute(it.id) }
                _state.value = _state.value?.copy(isFavorite = false)
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message)
            }
        }
    }

    private fun addToFavorite() {
        viewModelScope.launch {
            try {
                state.value?.course?.let { addFavoriteCourseUseCase.execute(it) }
                _state.value = _state.value?.copy(isFavorite = true)
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message)
            }
        }
    }

    private fun loadCourseDetails(courseId: String) {
        _state.value = _state.value?.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val course = getCourseDetailsUseCase.execute(courseId)
                _state.value = _state.value?.copy(course = course, isLoading = false)
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message, isLoading = false)
            }
        }
    }
}
