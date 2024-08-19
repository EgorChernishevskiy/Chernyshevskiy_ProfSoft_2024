package com.example.togetherapp.presentation.state

data class AuthState(
    val loginPhoneNumber: String = "",
    val loginPassword: String = "",
    val registerPhoneNumber: String = "",
    val registerPassword: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val isLoading: Boolean = false,
    val loginSuccess: Boolean = false,
    val registerSuccess: Boolean = false,
    val errorMessage: String? = null,
    val hasToken: Boolean = false
)
