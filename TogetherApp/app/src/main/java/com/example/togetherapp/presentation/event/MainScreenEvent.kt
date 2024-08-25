package com.example.togetherapp.presentation.event

sealed class MainScreenEvent {
    object LoadCourses : MainScreenEvent()
    object LoadNotes : MainScreenEvent()
    object ShowAllCourses : MainScreenEvent()
    object HideAllCourses : MainScreenEvent()
    object ShowAllNotes : MainScreenEvent()
    object HideAllNotes : MainScreenEvent()
}