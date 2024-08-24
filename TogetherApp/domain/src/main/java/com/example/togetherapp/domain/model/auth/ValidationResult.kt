package com.example.togetherapp.domain.model.auth

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)