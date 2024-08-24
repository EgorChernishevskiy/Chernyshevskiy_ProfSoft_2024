package com.example.togetherapp.domain.usecase.auth

import com.example.togetherapp.domain.model.auth.RegisterParams
import com.example.togetherapp.domain.repository.AuthRepository

class RegisterUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(params: RegisterParams): Result<String> {
        return authRepository.register(params)
    }
}
