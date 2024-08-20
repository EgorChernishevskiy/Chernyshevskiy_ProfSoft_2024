package com.example.togetherapp.presentation.intent

sealed class SplashScreenIntent {
    object CheckToken : SplashScreenIntent()
    object NavigateToHome : SplashScreenIntent()
    object NavigateToLogin : SplashScreenIntent()
}
