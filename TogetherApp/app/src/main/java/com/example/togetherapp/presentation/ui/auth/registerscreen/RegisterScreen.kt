package com.example.togetherapp.presentation.ui.auth.registerscreen

import androidx.compose.foundation.background
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.togetherapp.presentation.ui.auth.registerscreen.components.RegisterScreenContent
import com.example.togetherapp.presentation.viewmodel.AuthViewModel

@Composable
fun RegisterScreen(viewModel: AuthViewModel, navController: NavHostController) {
    RegisterScreenContent(
        viewModel = viewModel,
        navController = navController,
        modifier = Modifier
            .background(Color(0xFFFFD80C))
    )
}