package com.example.togetherapp.presentation.state

import com.example.togetherapp.domain.model.course.ChallengeDto
import com.example.togetherapp.domain.model.course.Course

data class CourseDetailsScreenState(
    val course: ChallengeDto? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isFavorite: Boolean = false,
    val attemptText: String = "",
    val attemptImageUrl: String = ""
)
