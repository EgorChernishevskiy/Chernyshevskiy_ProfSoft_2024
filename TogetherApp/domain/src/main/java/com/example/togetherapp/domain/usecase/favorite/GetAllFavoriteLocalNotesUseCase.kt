package com.example.togetherapp.domain.usecase.favorite

import com.example.togetherapp.domain.model.locnote.LocNote
import com.example.togetherapp.domain.repository.FavoriteRepository

class GetAllFavoriteLocalNotesUseCase(private val repository: FavoriteRepository) {
    suspend fun execute(): List<LocNote> {
        return repository.getAllFavoriteLocalNotes()
    }
}