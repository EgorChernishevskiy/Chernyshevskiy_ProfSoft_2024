package com.example.togetherapp.domain.usecase.auth

import com.example.togetherapp.domain.repository.TokenRepository

class CheckTokenUseCase(private val tokenRepository: TokenRepository) {
    operator fun invoke(): Boolean {
        return tokenRepository.getToken() != null
    }
}