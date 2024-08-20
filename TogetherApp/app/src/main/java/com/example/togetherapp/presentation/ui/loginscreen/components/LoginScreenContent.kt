package com.example.togetherapp.presentation.ui.loginscreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.togetherapp.R
import com.example.togetherapp.presentation.intent.AuthEvent
import com.example.togetherapp.presentation.ui.components.AuthTextField
import com.example.togetherapp.presentation.ui.components.Logo
import com.example.togetherapp.presentation.viewmodel.AuthViewModel

@Composable
fun LoginScreenContent(
    viewModel: AuthViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    if (viewModel.uiState.loginSuccess) {
        LaunchedEffect(Unit) {
            navController.navigate("home") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        viewModel.uiState.errorMessage?.let { errorMsg ->
            Text(
                text = errorMsg,
                color = Color.Red,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Логотип
        Logo(
            modifier = Modifier
                .padding(top = 94.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(69.dp))

        // Текст "Вход"
        Text(
            text = stringResource(R.string.login_text_label),
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Подтекст
        Text(
            text = stringResource(R.string.login_subtext_text_label),
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 14.sp,
            color = Color(0xFF333333),
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Поле "Номер телефона"
        AuthTextField(
            value = viewModel.uiState.loginPhoneNumber,
            onValueChange = { viewModel.handleEvent(AuthEvent.OnLoginPhoneNumberChange(it)) },
            label = stringResource(R.string.phone_number_textfield)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Поле "Пароль"
        AuthTextField(
            value = viewModel.uiState.loginPassword,
            onValueChange = { viewModel.handleEvent(AuthEvent.OnLoginPasswordChange(it)) },
            label = stringResource(R.string.password_textfield),
            isPassword = true
        )

        Spacer(modifier = Modifier.height(150.dp))

        // Кнопка "Войти"
        Button(
            onClick = {
                viewModel.handleEvent(
                    AuthEvent.Login(
                        viewModel.uiState.loginPhoneNumber,
                        viewModel.uiState.loginPassword
                    )
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF333333))
        ) {
            if (viewModel.uiState.isLoading) {
                CircularProgressIndicator()
            }
            else {
                Text(
                    text = stringResource(R.string.button_login_text_label),
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка "Регистрация"
        TextButton(
            onClick = { navController.navigate("register") },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(40.dp)
        ) {
            Text(
                text = stringResource(R.string.register_text_label),
                color = Color.Black,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}
