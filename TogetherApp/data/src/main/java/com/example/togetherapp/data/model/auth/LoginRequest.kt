package com.example.togetherapp.data.model.auth

data class LoginRequest(
    val phone: String,
    val passwordHashed: String
)