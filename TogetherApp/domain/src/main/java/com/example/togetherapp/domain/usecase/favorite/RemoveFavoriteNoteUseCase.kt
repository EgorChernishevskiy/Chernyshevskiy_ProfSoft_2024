package com.example.togetherapp.domain.usecase.favorite

import com.example.togetherapp.domain.model.comnote.Note
import com.example.togetherapp.domain.repository.FavoriteRepository

class RemoveFavoriteNoteUseCase(private val repository: FavoriteRepository) {
    suspend fun execute(noteId: String) {
        repository.removeFavoriteNote(noteId)
    }
}