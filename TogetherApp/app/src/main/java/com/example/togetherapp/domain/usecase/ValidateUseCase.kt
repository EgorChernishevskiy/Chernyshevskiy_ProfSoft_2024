package com.example.togetherapp.domain.usecase

class ValidateUseCase {

    fun validateLogin(phoneNumber: String, password: String): Boolean {
        return validatePhoneNumber(phoneNumber) && validatePassword(password)
    }

    fun validateRegister(firstName: String, lastName: String, phoneNumber: String, password: String): Boolean {
        return validateName(firstName) && validateName(lastName) &&
                validatePhoneNumber(phoneNumber) && validatePassword(password)
    }

    private fun validateName(name: String): Boolean {
        return name.isNotBlank() && name.length >= 2
    }

    private fun validatePhoneNumber(phoneNumber: String): Boolean {
        return phoneNumber.isNotBlank() && phoneNumber.length == 11 && phoneNumber.all { it.isDigit() }
    }

    private fun validatePassword(password: String): Boolean {
        return password.isNotBlank() && password.length >= 6
    }
}