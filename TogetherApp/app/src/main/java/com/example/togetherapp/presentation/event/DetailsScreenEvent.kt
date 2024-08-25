package com.example.togetherapp.presentation.event

sealed class DetailsScreenEvent {
    data class LoadCourseDetails(val courseId: String) : DetailsScreenEvent()
}