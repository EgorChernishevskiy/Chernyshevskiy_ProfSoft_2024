package com.example.togetherapp.data.auth.model

data class LoginRequest(
    val phone: String,
    val passwordHashed: String
)