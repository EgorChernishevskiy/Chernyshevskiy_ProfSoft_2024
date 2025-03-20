package com.example.togetherapp.data.model.course

data class ChallengeSubmissionDto(
    val id: Long,
    val challengeId: Long,
    val author: String,
    val image: String,
    val text: String
)