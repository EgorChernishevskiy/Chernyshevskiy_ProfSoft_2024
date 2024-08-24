package com.example.togetherapp.domain.usecase.auth

import com.example.togetherapp.domain.repository.TokenRepository

class SaveTokenUseCase(private val tokenRepository: TokenRepository) {
    operator fun invoke(token: String) {
        tokenRepository.saveToken(token)
    }
}