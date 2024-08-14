package com.example.togetherapp.domain.usecase

import com.example.togetherapp.data.model.LoginResponse
import com.example.togetherapp.domain.repository.AuthRepository

class LoginUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(phoneNumber: String, password: String): Result<LoginResponse> {
        return authRepository.login(phoneNumber, password)
    }
}
