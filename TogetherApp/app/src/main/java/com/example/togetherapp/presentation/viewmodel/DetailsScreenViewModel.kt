package com.example.togetherapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.togetherapp.domain.model.course.Course
import com.example.togetherapp.domain.usecase.course.GetCourseByIdUseCase
import com.example.togetherapp.presentation.event.DetailsScreenEvent
import com.example.togetherapp.presentation.state.DetailsScreenState
import kotlinx.coroutines.launch

class DetailsScreenViewModel(
    private val getCourseDetailsUseCase: GetCourseByIdUseCase
) : ViewModel() {

    private val _state = MutableLiveData<DetailsScreenState>(DetailsScreenState())
    val state: LiveData<DetailsScreenState> get() = _state

    fun handleEvent(event: DetailsScreenEvent) {
        when (event) {
            is DetailsScreenEvent.LoadCourseDetails -> {
                loadCourseDetails(event.courseId)
            }
        }
    }

    private fun loadCourseDetails(courseId: String) {
        _state.value = _state.value?.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val course = getCourseDetailsUseCase.execute(courseId)
                _state.value = DetailsScreenState(course = course, isLoading = false)
            } catch (e: Exception) {
                _state.value = DetailsScreenState(error = e.message, isLoading = false)
            }
        }
    }
}
