package com.example.togetherapp.domain.usecase.profile

import com.example.togetherapp.domain.repository.TokenRepository

class LogOutUseCase(private val tokenRepository: TokenRepository) {
    operator fun invoke() {
        tokenRepository.clearToken()
    }
}