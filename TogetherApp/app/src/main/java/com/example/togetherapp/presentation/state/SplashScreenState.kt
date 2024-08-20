package com.example.togetherapp.presentation.state

data class SplashScreenState(
    val isLoading: Boolean = true,
    val navigateToHome: Boolean = false,
    val navigateToLogin: Boolean = false
)
