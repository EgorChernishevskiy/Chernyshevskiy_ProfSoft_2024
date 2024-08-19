package com.example.togetherapp.data.model

data class LoginRequest(
    val phone: String,
    val passwordHashed: String
)