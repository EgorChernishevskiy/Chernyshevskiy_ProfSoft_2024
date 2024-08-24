package com.example.togetherapp.data.model.auth

data class RegisterRequest(
    val name: String,
    val surname: String,
    val phone: String,
    val passwordHashed: String,
    val avatar: String = ""
)
