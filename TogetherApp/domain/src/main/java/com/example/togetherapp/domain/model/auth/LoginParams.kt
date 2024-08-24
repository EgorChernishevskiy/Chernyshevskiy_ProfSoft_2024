package com.example.togetherapp.domain.model.auth

data class LoginParams(
    val phone: String,
    val passwordHashed: String
)
