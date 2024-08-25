package com.example.togetherapp.presentation.event

import com.example.togetherapp.domain.model.course.Course

sealed class MainScreenEvent {
    object LoadCourses : MainScreenEvent()
    object LoadNotes : MainScreenEvent()
    object ShowAllCourses : MainScreenEvent()
    object HideAllCourses : MainScreenEvent()
    object ShowAllNotes : MainScreenEvent()
    object HideAllNotes : MainScreenEvent()
    data class SelectCourse(val course: Course) : MainScreenEvent()
    object DeselectCourse : MainScreenEvent()
}