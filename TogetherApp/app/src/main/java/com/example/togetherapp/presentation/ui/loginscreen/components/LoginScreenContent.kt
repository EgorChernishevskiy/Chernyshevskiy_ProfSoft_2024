package com.example.togetherapp.presentation.ui.loginscreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
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
    val snackbarHostState = remember { SnackbarHostState() }

    if (viewModel.state.loginSuccess) {
        LaunchedEffect(Unit) {
            navController.navigate("home") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    LaunchedEffect(viewModel.state.errorMessage) {
        viewModel.state.errorMessage?.let { errorMsg ->
            val result = snackbarHostState.showSnackbar(
                message = errorMsg,
                actionLabel = "OK"
            )
            if (result == SnackbarResult.ActionPerformed) {
                viewModel.handleEvent(AuthEvent.OnErrorMessageClear(errorMsg))
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        SnackbarHost(hostState = snackbarHostState)

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
            value = viewModel.state.loginPhoneNumber,
            onValueChange = { viewModel.handleEvent(AuthEvent.OnLoginPhoneNumberChange(it)) },
            label = stringResource(R.string.phone_number_textfield),
            errorMessage = viewModel.state.loginPhoneNumberError
        )
        if (viewModel.state.loginPhoneNumberError != null) {
            Text(
                text = viewModel.state.loginPhoneNumberError!!,
                color = Color.Red,
                modifier = Modifier
                    .align(Alignment.End)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Поле "Пароль"
        AuthTextField(
            value = viewModel.state.loginPassword,
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
                        viewModel.state.loginPhoneNumber,
                        viewModel.state.loginPassword
                    )
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF333333))
        ) {
            if (viewModel.state.isLoading) {
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
