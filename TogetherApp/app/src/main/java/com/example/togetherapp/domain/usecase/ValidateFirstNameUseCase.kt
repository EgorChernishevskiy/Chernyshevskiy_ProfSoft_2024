package com.example.togetherapp.domain.usecase

import com.example.togetherapp.domain.model.ValidationResult

class ValidateFirstNameUseCase {

    fun execute(firstName: String): ValidationResult {
        if (firstName.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Имя не может быть пустым"
            )
        }
        if (firstName.length < 2) {
            return ValidationResult(
                successful = false,
                errorMessage = "Имя должно содержать минимум 2 символа"
            )
        }
        if (!firstName.matches(Regex("^[a-zA-Zа-яА-Я]+$"))) {
            return ValidationResult(
                successful = false,
                errorMessage = "Имя должно содержать только буквы"
            )
        }
        return ValidationResult(successful = true)
    }
}