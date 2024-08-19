package com.example.togetherapp.data.model

data class RegisterRequest(
    val name: String,
    val surname: String,
    val phone: String,
    val passwordHashed: String,
    val avatar: String
)
