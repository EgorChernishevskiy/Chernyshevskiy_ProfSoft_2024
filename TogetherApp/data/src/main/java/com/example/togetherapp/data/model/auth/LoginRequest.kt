package com.example.togetherapp.data.model.auth

data class LoginRequest(
    val email: String,
    val passwordHashed: String
)