package com.example.togetherapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.togetherapp.domain.model.LoginParams
import com.example.togetherapp.domain.model.RegisterParams
import com.example.togetherapp.domain.usecase.LoginUseCase
import com.example.togetherapp.domain.usecase.RegisterUseCase
import com.example.togetherapp.domain.usecase.SaveTokenUseCase
import com.example.togetherapp.domain.usecase.ValidateFirstNameUseCase
import com.example.togetherapp.domain.usecase.ValidateLastNameUseCase
import com.example.togetherapp.domain.usecase.ValidatePasswordUseCase
import com.example.togetherapp.domain.usecase.ValidatePhoneNumberUseCase
import com.example.togetherapp.domain.utils.hashPassword
import com.example.togetherapp.presentation.event.AuthEvent
import com.example.togetherapp.presentation.state.AuthState
import kotlinx.coroutines.launch

class AuthViewModel(
    private val loginUseCase: com.example.togetherapp.domain.usecase.LoginUseCase,
    private val registerUseCase: com.example.togetherapp.domain.usecase.RegisterUseCase,
    private val saveTokenUseCase: com.example.togetherapp.domain.usecase.SaveTokenUseCase,
    private val validateFirstNameUseCase: com.example.togetherapp.domain.usecase.ValidateFirstNameUseCase,
    private val validateLastNameUseCase: com.example.togetherapp.domain.usecase.ValidateLastNameUseCase,
    private val validatePhoneNumberUseCase: com.example.togetherapp.domain.usecase.ValidatePhoneNumberUseCase,
    private val validatePasswordUseCase: com.example.togetherapp.domain.usecase.ValidatePasswordUseCase
) : ViewModel() {

    private val _state = MutableLiveData(AuthState())
    val state: LiveData<AuthState> get() = _state

    fun handleEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.Login -> {
                _state.value = _state.value?.copy(
                    loginPhoneNumber = event.phoneNumber,
                    loginPassword = event.password
                )
                submitLogin()
            }

            is AuthEvent.Register -> {
                _state.value = _state.value?.copy(
                    firstName = event.firstName,
                    lastName = event.lastName,
                    registerPhoneNumber = event.phoneNumber,
                    registerPassword = event.password
                )
                submitRegister()
            }

            is AuthEvent.OnFirstNameChange -> {
                _state.value = _state.value?.copy(firstName = event.firstName)
                validateFirstName()
            }

            is AuthEvent.OnLastNameChange -> {
                _state.value = _state.value?.copy(lastName = event.lastName)
                validateLastName()
            }

            is AuthEvent.OnLoginPhoneNumberChange -> {
                _state.value = _state.value?.copy(loginPhoneNumber = event.phoneNumber)
                validateLoginPhoneNumber()
            }

            is AuthEvent.OnLoginPasswordChange -> {
                _state.value = _state.value?.copy(loginPassword = event.password)
            }

            is AuthEvent.OnRegisterPhoneNumberChange -> {
                _state.value = _state.value?.copy(registerPhoneNumber = event.phoneNumber)
                validateRegisterPhoneNumber()
            }

            is AuthEvent.OnRegisterPasswordChange -> {
                _state.value = _state.value?.copy(registerPassword = event.password)
                validateRegisterPassword()
            }

            is AuthEvent.OnErrorMessageClear -> {
                _state.value = _state.value?.copy(errorMessage = null)
            }
        }
    }

    private fun validateFirstName() {
        val firstNameResult = validateFirstNameUseCase.execute(_state.value?.firstName ?: "")
        _state.value = _state.value?.copy(firstNameError = firstNameResult.errorMessage)
    }

    private fun validateLastName() {
        val lastNameResult = validateLastNameUseCase.execute(_state.value?.lastName ?: "")
        _state.value = _state.value?.copy(lastNameError = lastNameResult.errorMessage)
    }

    private fun validateLoginPhoneNumber() {
        val phoneNumberResult = validatePhoneNumberUseCase.execute(_state.value?.loginPhoneNumber ?: "")
        _state.value = _state.value?.copy(loginPhoneNumberError = phoneNumberResult.errorMessage)
    }

    private fun validateRegisterPhoneNumber() {
        val phoneNumberResult = validatePhoneNumberUseCase.execute(_state.value?.registerPhoneNumber ?: "")
        _state.value = _state.value?.copy(registerPhoneNumberError = phoneNumberResult.errorMessage)
    }

    private fun validateRegisterPassword() {
        val passwordResult = validatePasswordUseCase.execute(_state.value?.registerPassword ?: "")
        _state.value = _state.value?.copy(registerPasswordError = passwordResult.errorMessage)
    }

    private fun submitLogin() {
        validateLoginPhoneNumber()

        if (_state.value?.loginPhoneNumberError != null || _state.value?.loginPasswordError != null) {
            return
        }

        val hashedPassword =
            com.example.togetherapp.domain.utils.hashPassword(_state.value?.loginPassword ?: "")
        val loginParams = com.example.togetherapp.domain.model.LoginParams(
            phone = _state.value?.loginPhoneNumber ?: "",
            passwordHashed = hashedPassword
        )
        login(loginParams)
    }

    private fun submitRegister() {
        validateFirstName()
        validateLastName()
        validateRegisterPhoneNumber()
        validateRegisterPassword()

        if (_state.value?.firstNameError != null || _state.value?.lastNameError != null ||
            _state.value?.registerPhoneNumberError != null || _state.value?.registerPasswordError != null
        ) {
            return
        }

        val hashedPassword =
            com.example.togetherapp.domain.utils.hashPassword(_state.value?.registerPassword ?: "")
        val registerParams = com.example.togetherapp.domain.model.RegisterParams(
            firstName = _state.value?.firstName ?: "",
            lastName = _state.value?.lastName ?: "",
            phoneNumber = _state.value?.registerPhoneNumber ?: "",
            password = hashedPassword
        )
        register(registerParams)
    }

    private fun login(params: com.example.togetherapp.domain.model.LoginParams) {
        viewModelScope.launch {
            _state.value = _state.value?.copy(isLoading = true)
            val result = loginUseCase(params)
            if (result.isSuccess) {
                saveTokenUseCase(result.getOrNull() ?: "")
                _state.value = _state.value?.copy(isLoading = false, loginSuccess = true)
            } else {
                _state.value =
                    _state.value?.copy(isLoading = false, errorMessage = result.exceptionOrNull()?.message)
            }
        }
    }

    private fun register(params: com.example.togetherapp.domain.model.RegisterParams) {
        viewModelScope.launch {
            _state.value = _state.value?.copy(isLoading = true)
            val result = registerUseCase(params)
            if (result.isSuccess) {
                saveTokenUseCase(result.getOrNull() ?: "")
                _state.value = _state.value?.copy(isLoading = false, registerSuccess = true)
            } else {
                _state.value =
                    _state.value?.copy(isLoading = false, errorMessage = result.exceptionOrNull()?.message)
            }
        }
    }
}
