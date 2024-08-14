package com.example.togetherapp.presentation.state

sealed class AuthEvent {
    data class Login(val phoneNumber: String, val password: String) : AuthEvent()
    data class Register(val firstName: String, val lastName: String, val phoneNumber: String, val password: String) : AuthEvent()
}