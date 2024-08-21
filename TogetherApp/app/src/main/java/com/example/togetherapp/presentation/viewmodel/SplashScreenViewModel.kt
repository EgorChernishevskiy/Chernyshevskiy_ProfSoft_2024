package com.example.togetherapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.togetherapp.domain.usecase.CheckTokenUseCase
import com.example.togetherapp.presentation.intent.SplashScreenIntent
import com.example.togetherapp.presentation.state.SplashScreenState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val SPLASH_DELAY: Long = 2000

class SplashScreenViewModel(
    private val checkTokenUseCase: CheckTokenUseCase
) : ViewModel() {
    private val _state = MutableLiveData(SplashScreenState())
    val state: LiveData<SplashScreenState> get() = _state

    init {
        viewModelScope.launch {
            delay(SPLASH_DELAY)
            handleIntent(SplashScreenIntent.CheckToken)
        }
    }

    private fun handleIntent(intent: SplashScreenIntent) {
        val newState = splashScreenReducer(_state.value!!, intent)
        _state.value = newState
    }

    private fun splashScreenReducer(
        currentState: SplashScreenState,
        intent: SplashScreenIntent
    ): SplashScreenState {
        return when (intent) {
            is SplashScreenIntent.CheckToken -> {
                val tokenValid = checkTokenUseCase()
                if (tokenValid) {
                    currentState.copy(isLoading = false, navigateToHome = true)
                } else {
                    currentState.copy(isLoading = false, navigateToLogin = true)
                }
            }

            is SplashScreenIntent.NavigateToHome -> {
                currentState.copy(navigateToHome = true)
            }

            is SplashScreenIntent.NavigateToLogin -> {
                currentState.copy(navigateToLogin = true)
            }
        }
    }
}
