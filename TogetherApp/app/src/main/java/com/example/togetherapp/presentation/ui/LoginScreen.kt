package com.example.togetherapp.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.togetherapp.R
import com.example.togetherapp.presentation.state.AuthEvent
import com.example.togetherapp.presentation.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(viewModel: AuthViewModel, navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFD80C)) // Желтый фон
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // Логотип
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 94.dp)
                .align(Alignment.CenterHorizontally)
                .width(61.dp)
                .height(70.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Текст "Вход"
        Text(
            text = "Вход",
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Подтекст
        Text(
            text = "Пожалуйста, войдите для продолжения",
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Поле "Номер телефона"
        TextField(
            value = viewModel.uiState.phoneNumber,
            onValueChange = { viewModel.handleEvent(AuthEvent.OnPhoneNumberChange(it)) },
            label = { Text("Номер телефона", color = Color.Black) },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFF59D)), // Светло-желтый фон поля
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFFFF59D),
                focusedIndicatorColor = Color.Gray,
                unfocusedIndicatorColor = Color.Gray,
                unfocusedTextColor = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Поле "Пароль"
        TextField(
            value = viewModel.uiState.password,
            onValueChange = { viewModel.handleEvent(AuthEvent.OnPasswordChange(it)) },
            label = { Text("Пароль", color = Color.Black) },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFF59D)), // Светло-желтый фон поля
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFFFF59D),
                focusedIndicatorColor = Color.Gray,
                unfocusedIndicatorColor = Color.Gray,
                unfocusedTextColor = Color.Black
            ),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка "Войти"
        Button(
            onClick = {
                viewModel.handleEvent(AuthEvent.Login(viewModel.uiState.phoneNumber, viewModel.uiState.password))
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text(text = "Войти", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка "Регистрация"
        TextButton(
            onClick = { navController.navigate("register") },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Регистрация", color = Color.Black)
        }
    }
}



