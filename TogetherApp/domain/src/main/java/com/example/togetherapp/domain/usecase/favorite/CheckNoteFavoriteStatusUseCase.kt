package com.example.togetherapp.domain.usecase.favorite

import com.example.togetherapp.domain.repository.FavoriteRepository

class CheckNoteFavoriteStatusUseCase(private val favoriteRepository: FavoriteRepository) {
    suspend fun execute(noteId: String): Boolean {
        return favoriteRepository.isNoteFavorite(noteId)
    }
}