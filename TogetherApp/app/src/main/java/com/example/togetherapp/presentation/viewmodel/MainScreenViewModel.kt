package com.example.togetherapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.togetherapp.domain.usecase.GetCoursesUseCase
import com.example.togetherapp.presentation.event.MainScreenEvent
import com.example.togetherapp.presentation.state.MainScreenState
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val getCoursesUseCase: GetCoursesUseCase
) : ViewModel() {

    private val _state = MutableLiveData(MainScreenState())
    val state: LiveData<MainScreenState> get() = _state

    fun handleEvent(event: MainScreenEvent) {
        when (event) {
            is MainScreenEvent.LoadCourses -> fetchCourses()
        }
    }

    private fun fetchCourses() {
        viewModelScope.launch {
            _state.value = MainScreenState(isLoading = true)
            try {
                val coursesList = getCoursesUseCase.execute()
                _state.value = MainScreenState(courses = coursesList.take(6))
            } catch (e: Exception) {
                _state.value = MainScreenState(error = e.message)
            } finally {
                _state.value = _state.value?.copy(isLoading = false)
            }
        }
    }
}