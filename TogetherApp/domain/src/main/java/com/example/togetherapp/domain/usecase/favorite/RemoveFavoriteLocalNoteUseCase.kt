package com.example.togetherapp.domain.usecase.favorite

import com.example.togetherapp.domain.repository.FavoriteRepository

class RemoveFavoriteLocalNoteUseCase(private val repository: FavoriteRepository) {
    suspend fun execute(noteId: Int) {
        repository.removeFavoriteLocalNote(noteId)
    }
}