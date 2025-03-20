package com.example.togetherapp.presentation.event

sealed class CourseDetailsScreenEvent {
    data class LoadCourseDetails(val courseId: String) : CourseDetailsScreenEvent()
    object AddToFavorite : CourseDetailsScreenEvent()
    object RemoveFromFavorite : CourseDetailsScreenEvent()
    data class CheckIfFavorite(val courseId: String) : CourseDetailsScreenEvent()
    data class UpdateAttemptText(val newText: String) : CourseDetailsScreenEvent()
    data class UpdateAttemptImageUrl(val newImageUrl: String) : CourseDetailsScreenEvent()
    data class SubmitAttempt(val challengeId: Long, val image: String, val text: String): CourseDetailsScreenEvent()

    object OnErrorClear : CourseDetailsScreenEvent()
}

