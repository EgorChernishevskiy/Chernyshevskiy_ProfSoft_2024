package com.example.togetherapp.presentation.ui.registerscreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.togetherapp.R
import com.example.togetherapp.presentation.event.AuthEvent
import com.example.togetherapp.presentation.state.AuthState
import com.example.togetherapp.presentation.ui.components.AuthTextField
import com.example.togetherapp.presentation.ui.components.Logo
import com.example.togetherapp.presentation.viewmodel.AuthViewModel

@Composable
fun RegisterScreenContent(
    viewModel: AuthViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.observeAsState(AuthState())

    if (state.registerSuccess) {
        LaunchedEffect(Unit) {
            navController.navigate("home") {
                popUpTo("register") { inclusive = true }
            }
        }
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        // Логотип
        Logo(
            modifier = Modifier
                .padding(top = 83.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(58.dp))

        // Текст "Регистрация"
        Text(
            text = stringResource(R.string.register_text_label),
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Подтекст
        Text(
            text = stringResource(R.string.register_subtext_label),
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 14.sp,
            color = Color(0xFF333333),
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Поле "Имя"
        AuthTextField(
            value = state.firstName,
            onValueChange = { viewModel.handleEvent(AuthEvent.OnFirstNameChange(it)) },
            label = stringResource(R.string.first_name_textfield),
            errorMessage = state.firstNameError
        )
        if (state.firstNameError != null) {
            Text(
                text = state.firstNameError!!,
                color = Color.Red,
                modifier = Modifier
                    .align(Alignment.End)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Поле "Фамилия"
        AuthTextField(
            value = state.lastName,
            onValueChange = { viewModel.handleEvent(AuthEvent.OnLastNameChange(it)) },
            label = stringResource(R.string.last_name_textfield),
            errorMessage = state.lastNameError
        )
        if (state.lastNameError != null) {
            Text(
                text = state.lastNameError!!,
                color = Color.Red,
                modifier = Modifier
                    .align(Alignment.End)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Поле "Номер телефона"
        AuthTextField(
            value = state.registerPhoneNumber,
            onValueChange = { viewModel.handleEvent(AuthEvent.OnRegisterPhoneNumberChange(it)) },
            label = stringResource(R.string.phone_number_textfield),
            errorMessage = state.registerPhoneNumberError
        )
        if (state.registerPhoneNumberError != null) {
            Text(
                text = state.registerPhoneNumberError!!,
                color = Color.Red,
                modifier = Modifier
                    .align(Alignment.End)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Поле "Пароль"
        AuthTextField(
            value = state.registerPassword,
            onValueChange = { viewModel.handleEvent(AuthEvent.OnRegisterPasswordChange(it)) },
            label = stringResource(R.string.password_textfield),
            isPassword = true
        )
        if (state.registerPasswordError != null) {
            Text(
                text = state.registerPasswordError!!,
                color = Color.Red,
                modifier = Modifier
                    .align(Alignment.End)
            )
        }

        Spacer(modifier = Modifier.height(60.dp))

        // Кнопка "Регистрация"
        Button(
            onClick = {
                viewModel.handleEvent(
                    AuthEvent.Register(
                        state.firstName,
                        state.lastName,
                        state.registerPhoneNumber,
                        state.registerPassword
                    )
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF333333))
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            } else {
                Text(
                    text = stringResource(R.string.register_text_label),
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка "Войти"
        TextButton(
            onClick = { navController.navigate("login") },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(40.dp)
        ) {
            Text(
                text = stringResource(R.string.login_with_account_text_label),
                color = Color.Black,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}