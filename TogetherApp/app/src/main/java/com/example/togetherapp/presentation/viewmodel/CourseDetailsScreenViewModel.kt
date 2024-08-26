package com.example.togetherapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.togetherapp.domain.usecase.course.GetCourseByIdUseCase
import com.example.togetherapp.presentation.event.CourseDetailsScreenEvent
import com.example.togetherapp.presentation.state.CourseDetailsScreenState
import kotlinx.coroutines.launch

class CourseDetailsScreenViewModel(
    private val getCourseDetailsUseCase: GetCourseByIdUseCase
) : ViewModel() {

    private val _state = MutableLiveData(CourseDetailsScreenState())
    val state: LiveData<CourseDetailsScreenState> get() = _state

    fun handleEvent(event: CourseDetailsScreenEvent) {
        when (event) {
            is CourseDetailsScreenEvent.LoadCourseDetails -> {
                loadCourseDetails(event.courseId)
            }
        }
    }

    private fun loadCourseDetails(courseId: String) {
        _state.value = _state.value?.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val course = getCourseDetailsUseCase.execute(courseId)
                _state.value = CourseDetailsScreenState(course = course, isLoading = false)
            } catch (e: Exception) {
                _state.value = CourseDetailsScreenState(error = e.message, isLoading = false)
            }
        }
    }
}
