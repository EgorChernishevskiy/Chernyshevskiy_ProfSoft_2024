package com.example.togetherapp.presentation.ui.splashscreen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.togetherapp.presentation.ui.splashscreen.components.SplashScreenContent

private const val SPLASH_DELAY : Long = 2000

@Composable
fun SplashScreen(navController: NavHostController) {
    SplashScreenContent()
    android.os.Handler().postDelayed({
        navController.navigate("login") {
            popUpTo("splash") { inclusive = true }
        }
    }, SPLASH_DELAY)
}
