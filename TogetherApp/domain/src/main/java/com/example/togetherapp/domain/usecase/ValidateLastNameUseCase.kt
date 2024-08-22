package com.example.togetherapp.domain.usecase

import com.example.togetherapp.domain.model.ValidationResult

class ValidateLastNameUseCase {

    fun execute(lastName: String): ValidationResult {
        if (lastName.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Фамилия не может быть пустой"
            )
        }
        if (lastName.length < 2) {
            return ValidationResult(
                successful = false,
                errorMessage = "Фамилия должна содержать минимум 2 символа"
            )
        }
        if (!lastName.matches(Regex("^[a-zA-Zа-яА-Я]+$"))) {
            return ValidationResult(
                successful = false,
                errorMessage = "Фамилия должна содержать только буквы"
            )
        }
        return ValidationResult(successful = true)
    }
}
