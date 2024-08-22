package com.example.togetherapp.domain.model

data class RegisterParams(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val password: String
)
