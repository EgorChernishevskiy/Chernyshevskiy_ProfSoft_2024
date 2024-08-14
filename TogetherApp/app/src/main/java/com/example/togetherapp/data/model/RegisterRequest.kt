package com.example.togetherapp.data.model

data class RegisterRequest(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val password: String
)
