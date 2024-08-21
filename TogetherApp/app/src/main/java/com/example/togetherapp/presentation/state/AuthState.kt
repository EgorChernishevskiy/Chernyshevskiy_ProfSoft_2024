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
    val firstNameError: String? = null,
    val lastNameError: String? = null,
    val loginPhoneNumberError: String? = null,
    val loginPasswordError: String? = null,
    val registerPhoneNumberError: String? = null,
    val registerPasswordError: String? = null,
    val hasToken: Boolean = false
)
