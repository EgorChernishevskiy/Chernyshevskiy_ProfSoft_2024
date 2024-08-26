package com.example.togetherapp.presentation.ui.mainscreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.togetherapp.presentation.ui.auth.loginscreen.components.LoginScreenContent
import com.example.togetherapp.presentation.ui.mainscreen.components.MainScreenContent
import com.example.togetherapp.presentation.viewmodel.AuthViewModel
import com.example.togetherapp.presentation.viewmodel.MainScreenViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(viewModel: MainScreenViewModel, navController: NavHostController) {
    MainScreenContent(viewModel, navController)
}
