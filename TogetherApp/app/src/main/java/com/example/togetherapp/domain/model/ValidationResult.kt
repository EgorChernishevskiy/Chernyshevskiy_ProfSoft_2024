package com.example.togetherapp.domain.model

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)