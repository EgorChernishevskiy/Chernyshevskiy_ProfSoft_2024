package com.example.togetherapp.data.model.profile

import com.example.togetherapp.data.model.course.CourseDto
import com.example.togetherapp.data.model.note.NoteDto

data class UserProfileDto(
    val id: String,
    val name: String,
    val surname: String,
    val avatar: String?,
    val email: String?,
    val notes: List<NoteDto>?
)