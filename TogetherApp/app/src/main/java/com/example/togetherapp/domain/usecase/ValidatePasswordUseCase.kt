package com.example.togetherapp.domain.usecase

import com.example.togetherapp.domain.model.ValidationResult

class ValidatePasswordUseCase {

    fun execute(password: String): ValidationResult {
        if (password.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = "Пароль должен содержать минимум 8 символов"
            )
        }
        val containsLettersAndDigits = password.any { it.isDigit() } &&
                password.any { it.isLetter() }
        if (!containsLettersAndDigits) {
            return ValidationResult(
                successful = false,
                errorMessage = "Пароль должен содержать хотя бы одну букву и одну цифру"
            )
        }
        return ValidationResult(
            successful = true
        )
    }
}