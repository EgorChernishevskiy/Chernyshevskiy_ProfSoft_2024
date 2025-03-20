package com.example.togetherapp.domain.model.course

import com.example.togetherapp.domain.utils.NoteTopic

data class ChallengeDto(
    val id: Long,
    val technique: NoteTopic,
    val theme: String,
    val palette: String,
    val createdDate: String,
    val submissions: List<ChallengeSubmissionDto>
)
