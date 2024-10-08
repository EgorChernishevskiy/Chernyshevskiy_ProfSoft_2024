package com.example.togetherapp.presentation.state

import com.example.togetherapp.domain.model.course.Course

data class CourseDetailsScreenState(
    val course: Course? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isFavorite: Boolean = false
)
