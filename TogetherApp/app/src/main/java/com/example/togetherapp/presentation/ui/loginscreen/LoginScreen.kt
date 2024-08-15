package com.example.togetherapp.presentation.ui.loginscreen

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.togetherapp.presentation.ui.loginscreen.components.LoginScreenContent
import com.example.togetherapp.presentation.viewmodel.AuthViewModel

@Composable
fun LoginScreen(viewModel: AuthViewModel, navController: NavHostController) {
    LoginScreenContent(
        viewModel = viewModel,
        navController = navController,
        modifier = Modifier
            .background(Color(0xFFFFD80C))
    )
}
