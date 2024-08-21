package com.example.togetherapp.domain.usecase

import com.example.togetherapp.domain.model.LoginParams
import com.example.togetherapp.domain.repository.AuthRepository

class LoginUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(params: LoginParams): Result<String> {
        return authRepository.login(params)
    }
}
