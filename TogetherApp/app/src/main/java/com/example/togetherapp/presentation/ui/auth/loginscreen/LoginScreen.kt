package com.example.togetherapp.presentation.ui.auth.loginscreen

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.togetherapp.presentation.ui.auth.loginscreen.components.LoginScreenContent

@Composable
fun LoginScreen(navController: NavHostController) {
    LoginScreenContent(
        navController = navController,
        modifier = Modifier
            .background(Color(0xFFFFD80C))
    )
}
