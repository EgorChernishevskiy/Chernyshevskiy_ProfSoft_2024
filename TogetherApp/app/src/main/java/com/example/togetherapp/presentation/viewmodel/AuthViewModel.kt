package com.example.togetherapp.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.togetherapp.domain.usecase.LoginUseCase
import com.example.togetherapp.domain.usecase.RegisterUseCase
import com.example.togetherapp.domain.usecase.SaveTokenUseCase
import com.example.togetherapp.domain.usecase.ValidateFirstNameUseCase
import com.example.togetherapp.domain.usecase.ValidateLastNameUseCase
import com.example.togetherapp.domain.usecase.ValidatePasswordUseCase
import com.example.togetherapp.domain.usecase.ValidatePhoneNumberUseCase
import com.example.togetherapp.domain.utils.hashPassword
import com.example.togetherapp.presentation.intent.AuthEvent
import com.example.togetherapp.presentation.state.AuthState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val saveTokenUseCase: SaveTokenUseCase,
    private val validateFirstNameUseCase: ValidateFirstNameUseCase,
    private val validateLastNameUseCase: ValidateLastNameUseCase,
    private val validatePhoneNumberUseCase: ValidatePhoneNumberUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase
) : ViewModel() {

    var state by mutableStateOf(AuthState())

    fun handleEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.Login -> {
                state = state.copy(
                    loginPhoneNumber = event.phoneNumber,
                    loginPassword = event.password
                )
                submitLogin()
            }
            is AuthEvent.Register -> {
                state = state.copy(
                    firstName = event.firstName,
                    lastName = event.lastName,
                    registerPhoneNumber = event.phoneNumber,
                    registerPassword = event.password
                )
                submitRegister()
            }
            is AuthEvent.OnFirstNameChange -> {
                state = state.copy(firstName = event.firstName)
                validateFirstName()
            }
            is AuthEvent.OnLastNameChange -> {
                state = state.copy(lastName = event.lastName)
                validateLastName()
            }
            is AuthEvent.OnLoginPhoneNumberChange -> {
                state = state.copy(loginPhoneNumber = event.phoneNumber)
                validateLoginPhoneNumber()
            }
            is AuthEvent.OnLoginPasswordChange -> {
                state = state.copy(loginPassword = event.password)
            }
            is AuthEvent.OnRegisterPhoneNumberChange -> {
                state = state.copy(registerPhoneNumber = event.phoneNumber)
                validateRegisterPhoneNumber()
            }
            is AuthEvent.OnRegisterPasswordChange -> {
                state = state.copy(registerPassword = event.password)
                validateRegisterPassword()
            }
            is AuthEvent.OnErrorMessageClear -> {
                state = state.copy(errorMessage = null)
            }
        }
    }

    private fun validateFirstName() {
        val firstNameResult = validateFirstNameUseCase.execute(state.firstName)
        state = state.copy(firstNameError = firstNameResult.errorMessage)
    }

    private fun validateLastName() {
        val lastNameResult = validateLastNameUseCase.execute(state.lastName)
        state = state.copy(lastNameError = lastNameResult.errorMessage)
    }

    private fun validateLoginPhoneNumber() {
        val phoneNumberResult = validatePhoneNumberUseCase.execute(state.loginPhoneNumber)
        state = state.copy(loginPhoneNumberError = phoneNumberResult.errorMessage)
    }

    private fun validateRegisterPhoneNumber() {
        val phoneNumberResult = validatePhoneNumberUseCase.execute(state.registerPhoneNumber)
        state = state.copy(registerPhoneNumberError = phoneNumberResult.errorMessage)
    }

    private fun validateRegisterPassword() {
        val passwordResult = validatePasswordUseCase.execute(state.registerPassword)
        state = state.copy(registerPasswordError = passwordResult.errorMessage)
    }

    private fun submitLogin() {
        validateLoginPhoneNumber()

        if (state.loginPhoneNumberError != null || state.loginPasswordError != null) {
            return
        }

        val hashedPassword = hashPassword(state.loginPassword)
        login(state.loginPhoneNumber, hashedPassword)
    }

    private fun submitRegister() {
        validateFirstName()
        validateLastName()
        validateRegisterPhoneNumber()
        validateRegisterPassword()

        if (state.firstNameError != null || state.lastNameError != null ||
            state.registerPhoneNumberError != null || state.registerPasswordError != null) {
            return
        }

        val hashedPassword = hashPassword(state.registerPassword)
        register(state.firstName, state.lastName, state.registerPhoneNumber, hashedPassword)
    }

    private fun login(phoneNumber: String, password: String) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = loginUseCase(phoneNumber, password)
            if (result.isSuccess) {
                saveTokenUseCase(result.getOrNull() ?: "")
                state = state.copy(isLoading = false, loginSuccess = true)
            } else {
                state = state.copy(isLoading = false, errorMessage = result.exceptionOrNull()?.message)
            }
        }
    }

    private fun register(firstName: String, lastName: String, phoneNumber: String, password: String) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = registerUseCase(firstName, lastName, phoneNumber, password)
            if (result.isSuccess) {
                saveTokenUseCase(result.getOrNull() ?: "")
                state = state.copy(isLoading = false, registerSuccess = true)
            } else {
                state = state.copy(isLoading = false, errorMessage = result.exceptionOrNull()?.message)
            }
        }
    }
}
