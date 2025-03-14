package com.example.togetherapp.data.model.auth

data class RegisterRequest(
    val name: String,
    val surname: String,
    val email: String,
    val passwordHashed: String
)
