package com.example.togetherapp.domain.usecase.favorite

import com.example.togetherapp.domain.model.comnote.Note
import com.example.togetherapp.domain.model.course.Course
import com.example.togetherapp.domain.repository.FavoriteRepository

class GetAllFavoritesUseCase(private val repository: FavoriteRepository) {
    suspend fun execute(): Pair<List<Course>, List<Note>> {
        return repository.getAllFavorites()
    }
}