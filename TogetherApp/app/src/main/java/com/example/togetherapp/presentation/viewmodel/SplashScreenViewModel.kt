package com.example.togetherapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.togetherapp.domain.usecase.auth.CheckTokenUseCase
import com.example.togetherapp.presentation.event.SplashScreenEvent
import com.example.togetherapp.presentation.state.SplashScreenState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val SPLASH_DELAY: Long = 1000

class SplashScreenViewModel(
    private val checkTokenUseCase: CheckTokenUseCase
) : ViewModel() {

    private val _state = MutableLiveData(SplashScreenState())
    val state: LiveData<SplashScreenState> get() = _state

    init {
        viewModelScope.launch {
            delay(SPLASH_DELAY)
            handleEvent(SplashScreenEvent.CheckToken)
        }
    }

    fun handleEvent(event: SplashScreenEvent) {
        when (event) {
            is SplashScreenEvent.CheckToken -> {
                checkToken()
            }
        }
    }

    private fun checkToken() {
        viewModelScope.launch {
            _state.value = _state.value?.copy(isLoading = true)
            val tokenValid = checkTokenUseCase()

            if (tokenValid) {
                _state.value = _state.value?.copy(
                    isLoading = false,
                    navigateToHome = true
                )
            } else {
                _state.value = _state.value?.copy(
                    isLoading = false,
                    navigateToLogin = true
                )
            }
        }
    }
}
