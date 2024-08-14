package com.example.togetherapp.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.togetherapp.domain.usecase.LoginUseCase
import com.example.togetherapp.domain.usecase.RegisterUseCase
import com.example.togetherapp.presentation.state.AuthEvent
import com.example.togetherapp.presentation.state.AuthState
import kotlinx.coroutines.launch

class AuthViewModel(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    var uiState by mutableStateOf(AuthState())
        private set

    fun handleEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.Login -> login(event.phoneNumber, event.password)
            is AuthEvent.Register -> register(event.firstName, event.lastName, event.phoneNumber, event.password)
        }
    }

    private fun login(phoneNumber: String, password: String) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            val result = loginUseCase(phoneNumber, password)
            uiState = if (result.isSuccess) {
                uiState.copy(isLoading = false, loginSuccess = true)
            } else {
                uiState.copy(isLoading = false, errorMessage = result.exceptionOrNull()?.message)
            }
        }
    }

    private fun register(firstName: String, lastName: String, phoneNumber: String, password: String) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            val result = registerUseCase(firstName, lastName, phoneNumber, password)
            uiState = if (result.isSuccess) {
                uiState.copy(isLoading = false, registerSuccess = true)
            } else {
                uiState.copy(isLoading = false, errorMessage = result.exceptionOrNull()?.message)
            }
        }
    }
}