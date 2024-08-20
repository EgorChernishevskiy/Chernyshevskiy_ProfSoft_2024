package com.example.togetherapp.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.togetherapp.domain.usecase.CheckTokenUseCase
import com.example.togetherapp.domain.usecase.LoginUseCase
import com.example.togetherapp.domain.usecase.RegisterUseCase
import com.example.togetherapp.domain.usecase.SaveTokenUseCase
import com.example.togetherapp.domain.usecase.ValidateUseCase
import com.example.togetherapp.domain.utils.hashPassword
import com.example.togetherapp.presentation.intent.AuthEvent
import com.example.togetherapp.presentation.state.AuthState
import kotlinx.coroutines.launch

class AuthViewModel(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val checkTokenUseCase: CheckTokenUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val validateUseCase: ValidateUseCase
) : ViewModel() {

    var uiState by mutableStateOf(AuthState())
        private set

    init {
        checkToken()
    }

    fun handleEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.Login -> {
                if (validateUseCase.validateLogin(event.phoneNumber, event.password)) {
                    val hashedPassword = hashPassword(event.password)
                    login(event.phoneNumber, hashedPassword)
                } else {
                    uiState = uiState.copy(errorMessage = "Неверный номер телефона или пароль")
                }
            }
            is AuthEvent.Register -> {
                if (validateUseCase.validateRegister(event.firstName, event.lastName, event.phoneNumber, event.password)) {
                    val hashedPassword = hashPassword(event.password)
                    register(event.firstName, event.lastName, event.phoneNumber, hashedPassword)
                } else {
                    uiState = uiState.copy(errorMessage = "Invalid registration details")
                }
            }
            is AuthEvent.OnFirstNameChange -> uiState = uiState.copy(firstName = event.firstName)
            is AuthEvent.OnLastNameChange -> uiState = uiState.copy(lastName = event.lastName)
            is AuthEvent.OnLoginPhoneNumberChange -> uiState = uiState.copy(loginPhoneNumber = event.phoneNumber)
            is AuthEvent.OnLoginPasswordChange -> uiState = uiState.copy(loginPassword = event.password)
            is AuthEvent.OnRegisterPhoneNumberChange -> uiState = uiState.copy(registerPhoneNumber = event.phoneNumber)
            is AuthEvent.OnRegisterPasswordChange -> uiState = uiState.copy(registerPassword = event.password)
        }
    }

    private fun login(phoneNumber: String, password: String) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            val result = loginUseCase(phoneNumber, password)
            if (result.isSuccess) {
                saveTokenUseCase(result.getOrNull() ?: "")
                uiState = uiState.copy(isLoading = false, loginSuccess = true)
            } else {
                uiState = uiState.copy(isLoading = false, errorMessage = result.exceptionOrNull()?.message)
                println("Login failed: ${result.exceptionOrNull()?.message}")
                Log.e("LoginError", "Response: $result")
            }
        }
    }

    private fun register(firstName: String, lastName: String, phoneNumber: String, password: String) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            val result = registerUseCase(firstName, lastName, phoneNumber, password)
            if (result.isSuccess) {
                saveTokenUseCase(result.getOrNull() ?: "")
                uiState = uiState.copy(isLoading = false, registerSuccess = true)
            } else {
                uiState = uiState.copy(isLoading = false, errorMessage = result.exceptionOrNull()?.message)
                println("Registration failed: ${result.exceptionOrNull()?.message}")
                Log.e("LoginError", "Response: $result")

            }
        }
    }

    private fun checkToken() {
        viewModelScope.launch {
            val hasToken = checkTokenUseCase()
            uiState = uiState.copy(hasToken = hasToken)
        }
    }
}
