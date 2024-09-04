package com.example.togetherapp.presentation.event

sealed class CourseDetailsScreenEvent {
    data class LoadCourseDetails(val courseId: String) : CourseDetailsScreenEvent()
    object AddToFavorite : CourseDetailsScreenEvent()
    object RemoveFromFavorite : CourseDetailsScreenEvent()
    data class CheckIfFavorite(val courseId: String) : CourseDetailsScreenEvent()
    object OnErrorClear : CourseDetailsScreenEvent()
}

