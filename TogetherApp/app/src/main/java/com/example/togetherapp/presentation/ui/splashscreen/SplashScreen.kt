package com.example.togetherapp.presentation.ui.splashscreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.togetherapp.presentation.ui.splashscreen.components.SplashScreenContent
import com.example.togetherapp.presentation.viewmodel.SplashScreenViewModel


@Composable
fun SplashScreen(navController: NavHostController, viewModel: SplashScreenViewModel) {
    SplashScreenContent(Modifier,navController, viewModel)
}
