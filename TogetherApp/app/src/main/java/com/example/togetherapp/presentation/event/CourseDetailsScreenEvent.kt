package com.example.togetherapp.presentation.event

sealed class CourseDetailsScreenEvent {
    data class LoadCourseDetails(val courseId: String) : CourseDetailsScreenEvent()
}