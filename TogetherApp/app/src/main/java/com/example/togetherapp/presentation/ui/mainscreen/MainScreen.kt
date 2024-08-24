package com.example.togetherapp.presentation.ui.mainscreen

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.togetherapp.presentation.ui.loginscreen.components.LoginScreenContent
import com.example.togetherapp.presentation.ui.mainscreen.components.MainScreenContent
import com.example.togetherapp.presentation.viewmodel.AuthViewModel
import com.example.togetherapp.presentation.viewmodel.MainScreenViewModel

@Composable
fun MainScreen(viewModel: MainScreenViewModel, navController: NavHostController) {
    MainScreenContent(viewModel)
}
