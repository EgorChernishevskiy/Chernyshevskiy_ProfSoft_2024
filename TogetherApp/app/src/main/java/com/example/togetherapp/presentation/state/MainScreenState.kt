package com.example.togetherapp.presentation.state

import com.example.togetherapp.domain.model.course.Course
import com.example.togetherapp.domain.model.note.Note

data  class MainScreenState (
    val courses: List<Course> = emptyList(),
    val communityNote: Note? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val showAllCourses: Boolean = false
)