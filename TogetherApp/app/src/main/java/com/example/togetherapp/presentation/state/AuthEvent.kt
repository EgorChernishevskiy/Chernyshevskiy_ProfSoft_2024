package com.example.togetherapp.presentation.state

sealed class AuthEvent {
    data class OnPhoneNumberChange(val phoneNumber: String) : AuthEvent()
    data class OnPasswordChange(val password: String) : AuthEvent()
    data class OnFirstNameChange(val firstName: String) : AuthEvent()
    data class OnLastNameChange(val lastName: String) : AuthEvent()
    data class Login(val phoneNumber: String, val password: String) : AuthEvent()
    data class Register(val firstName: String, val lastName: String, val phoneNumber: String, val password: String) : AuthEvent()
}