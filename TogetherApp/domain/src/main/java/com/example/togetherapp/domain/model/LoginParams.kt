package com.example.togetherapp.domain.model

data class LoginParams(
    val phone: String,
    val passwordHashed: String
)
