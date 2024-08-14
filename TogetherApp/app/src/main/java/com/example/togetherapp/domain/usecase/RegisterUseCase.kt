package com.example.togetherapp.domain.usecase

import com.example.togetherapp.data.model.RegisterResponse
import com.example.togetherapp.domain.repository.AuthRepository

class RegisterUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(firstName: String, lastName: String, phoneNumber: String, password: String): Result<RegisterResponse> {
        return authRepository.register(firstName, lastName, phoneNumber, password)
    }
}
