package com.example.togetherapp.presentation.state

import com.example.togetherapp.domain.model.Course

data  class MainScreenState (
    val courses: List<Course> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)