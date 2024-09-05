package com.example.togetherapp.presentation.ui.splashscreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.togetherapp.presentation.ui.splashscreen.components.SplashScreenContent


@Composable
fun SplashScreen(navController: NavHostController) {
    SplashScreenContent(Modifier, navController)
}
