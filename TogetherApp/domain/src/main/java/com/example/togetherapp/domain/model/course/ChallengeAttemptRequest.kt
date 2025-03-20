package com.example.togetherapp.domain.model.course

data class ChallengeAttemptRequest(
    val challengeId: Long,
    val image: String,
    val text: String
)