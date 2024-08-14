package com.example.togetherapp.presentation.state

data class AuthState(
    val phoneNumber: String = "",
    val password: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val isLoading: Boolean = false,
    val loginSuccess: Boolean = false,
    val registerSuccess: Boolean = false,
    val errorMessage: String? = null
)
