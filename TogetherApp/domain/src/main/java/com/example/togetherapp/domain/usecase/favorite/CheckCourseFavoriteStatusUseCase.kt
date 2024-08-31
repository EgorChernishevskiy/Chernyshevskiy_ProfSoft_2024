package com.example.togetherapp.domain.usecase.favorite

import com.example.togetherapp.domain.repository.FavoriteRepository

class CheckCourseFavoriteStatusUseCase(
    private val favoriteRepository: FavoriteRepository
) {
    suspend fun execute(courseId: String): Boolean {
        return favoriteRepository.isCourseFavorite(courseId)
    }
}