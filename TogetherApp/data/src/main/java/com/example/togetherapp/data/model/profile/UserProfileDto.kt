package com.example.togetherapp.data.model.profile

import com.example.togetherapp.data.model.course.CourseDto
import com.example.togetherapp.data.model.note.NoteDto
import com.example.togetherapp.domain.model.course.ChallengeSubmissionDto

data class UserProfileDto(
    val id: String,
    val name: String,
    val surname: String,
    val avatar: String?,
    val email: String?,
    val notes: List<NoteDto>?,
    val submissions: List<ChallengeSubmissionDto>?
)