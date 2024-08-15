package com.example.togetherapp.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.togetherapp.presentation.ui.loginscreen.LoginScreen
import com.example.togetherapp.presentation.ui.registerscreen.RegisterScreen
import com.example.togetherapp.presentation.viewmodel.AuthViewModel

@Composable
fun NavGraph(navController: NavHostController, authViewModel: AuthViewModel) {
    NavHost(navController, startDestination = "login") {
        composable("login") { LoginScreen(viewModel = authViewModel, navController = navController) }
        composable("register") { RegisterScreen(viewModel = authViewModel, navController = navController) }
    }
}