package com.example.togetherapp.presentation.ui.auth.loginscreen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
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
import com.example.togetherapp.presentation.ui.navigation.Routes
import com.example.togetherapp.presentation.viewmodel.AuthViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreenContent(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val viewModel: AuthViewModel = koinViewModel()
    val state by viewModel.state.observeAsState(AuthState())
    val snackbarHostState = remember { SnackbarHostState() }

    if (state.loginSuccess) {
        LaunchedEffect(Unit) {
            navController.navigate(Routes.Home) {
                popUpTo(Routes.Login) { inclusive = true }
            }
        }
    }

    LaunchedEffect(state.loginErrorMessage) {
        state.loginErrorMessage?.let { errorMsg ->
            val result = snackbarHostState.showSnackbar(
                message = errorMsg,
                actionLabel = "OK"
            )
            if (result == SnackbarResult.ActionPerformed) {
                viewModel.handleEvent(AuthEvent.OnLoginErrorMessageClear(errorMsg))
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

        Logo(
            modifier = Modifier
                .padding(top = 94.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(69.dp))

        Text(
            text = stringResource(R.string.login_text_label),
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.login_subtext_text_label),
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 14.sp,
            color = Color(0xFF333333),
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(12.dp))

        AuthTextField(
            value = state.loginPhoneNumber,
            onValueChange = { viewModel.handleEvent(AuthEvent.OnLoginPhoneNumberChange(it)) },
            label = stringResource(R.string.phone_number_textfield),
            errorMessage = state.loginPhoneNumberError
        )
        if (state.loginPhoneNumberError != null) {
            Text(
                text = state.loginPhoneNumberError!!,
                color = Color.Red,
                modifier = Modifier
                    .align(Alignment.End)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        AuthTextField(
            value = state.loginPassword,
            onValueChange = { viewModel.handleEvent(AuthEvent.OnLoginPasswordChange(it)) },
            label = stringResource(R.string.password_textfield),
            isPassword = true
        )

        Spacer(modifier = Modifier.height(150.dp))

        Button(
            onClick = {
                viewModel.handleEvent(
                    AuthEvent.Login(
                        state.loginPhoneNumber,
                        state.loginPassword
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
                CircularProgressIndicator(
                    modifier = Modifier.size(30.dp),
                    color = Color(0xFFD6B714)
                )
            } else {
                Text(
                    text = stringResource(R.string.button_login_text_label),
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = { navController.navigate(Routes.Register) },
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
