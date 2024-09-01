package com.example.togetherapp.presentation.state

import com.example.togetherapp.domain.model.comnote.Note
import com.example.togetherapp.domain.model.course.Course
import com.example.togetherapp.domain.model.locnote.LocNote

data class FavoriteScreenState (
    val courses: List<Course> = emptyList(),
    val notes: List<Note> = emptyList(),
    val localNotes: List<LocNote> = emptyList(),
    val communityNote: Note? = null,
    val localNote: LocNote? = null,
    val isLoading: Boolean = true,
    val error: String? = null,
    val showAllCourses: Boolean = false,
    val showAllNotes: Boolean = false,
    val showAllLocalNotes: Boolean = false,
    val isDataLoaded: Boolean = false
)