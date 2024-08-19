package com.example.togetherapp.domain.usecase

import com.example.togetherapp.domain.repository.AuthRepository

class LoginUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(phone: String, passwordHashed: String): Result<String> {
        return authRepository.login(phone, passwordHashed)
    }
}
