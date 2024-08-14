package com.example.togetherapp.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.togetherapp.presentation.state.AuthEvent
import com.example.togetherapp.presentation.viewmodel.AuthViewModel

@Composable
fun LoginScreen(viewModel: AuthViewModel, navController: NavHostController) {
    val uiState = viewModel.uiState
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Вход", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(value = "", onValueChange = { /* Handle input */ }, label = { Text("Номер телефона") })
        TextField(value = "", onValueChange = { /* Handle input */ }, label = { Text("Пароль") }, visualTransformation = PasswordVisualTransformation())
        Button(onClick = { viewModel.handleEvent(AuthEvent.Login("phone", "password")) }) {
            if (uiState.isLoading) {
                CircularProgressIndicator()
            } else {
                Text("Войти")
            }
        }
        uiState.errorMessage?.let {
            Text(it, color = Color.Red)
        }
    }
}