package com.example.togetherapp.presentation.state

import com.example.togetherapp.domain.model.course.Course

data class DetailsScreenState(
    val course: Course? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
