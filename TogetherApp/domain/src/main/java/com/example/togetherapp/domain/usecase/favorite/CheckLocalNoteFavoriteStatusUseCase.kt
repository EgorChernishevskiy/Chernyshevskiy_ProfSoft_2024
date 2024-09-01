package com.example.togetherapp.domain.usecase.favorite

import com.example.togetherapp.domain.repository.FavoriteRepository

class CheckLocalNoteFavoriteStatusUseCase(
    private val favoriteRepository: FavoriteRepository
) {
    suspend fun execute(noteId: Int): Boolean {
        return favoriteRepository.isLocalNoteFavorite(noteId)
    }
}