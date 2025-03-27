package com.example.togetherapp.domain.model.profile

import com.example.togetherapp.domain.model.comnote.Note
import com.example.togetherapp.domain.model.course.ChallengeSubmissionDto
import com.example.togetherapp.domain.model.course.Course

data class UserProfile(
    val id: String,
    val name: String,
    val surname: String,
    val avatar: String?,
    val email: String?,
    val notes: List<Note>,
    val submissions: List<ChallengeSubmissionDto>
)