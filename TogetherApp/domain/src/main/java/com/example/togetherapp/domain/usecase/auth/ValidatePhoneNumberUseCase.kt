package com.example.togetherapp.domain.usecase.auth

import com.example.togetherapp.domain.model.auth.ValidationResult


class ValidatePhoneNumberUseCase {

    fun execute(phoneNumber: String): ValidationResult {
        if (phoneNumber.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = "Номер телефона не может быть пустым"
            )
        }
        if (!phoneNumber.matches(Regex("^[0-9]{11}\$"))) {
            return ValidationResult(
                successful = false,
                errorMessage = "Некорректный номер телефона. Номер должен содержать ровно 11 цифр."
            )
        }
        return ValidationResult(successful = true)
    }
}
