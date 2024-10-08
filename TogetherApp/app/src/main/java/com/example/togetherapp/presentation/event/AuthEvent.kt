package com.example.togetherapp.presentation.event

sealed class AuthEvent {
    data class Login(val phoneNumber: String, val password: String) : AuthEvent()
    data class Register(val firstName: String, val lastName: String, val phoneNumber: String, val password: String) : AuthEvent()
    data class OnFirstNameChange(val firstName: String) : AuthEvent()
    data class OnLastNameChange(val lastName: String) : AuthEvent()
    data class OnLoginPhoneNumberChange(val phoneNumber: String) : AuthEvent()
    data class OnLoginPasswordChange(val password: String) : AuthEvent()
    data class OnRegisterPhoneNumberChange(val phoneNumber: String) : AuthEvent()
    data class OnRegisterPasswordChange(val password: String) : AuthEvent()
    data class OnLoginErrorMessageClear(val errorMessage: String): AuthEvent()
    data class OnRegisterErrorMessageClear(val errorMessage: String): AuthEvent()
}