package com.example.togetherapp.domain.usecase.favorite

import com.example.togetherapp.domain.model.comnote.Note
import com.example.togetherapp.domain.repository.FavoriteRepository

class AddFavoriteNoteUseCase(private val repository: FavoriteRepository) {
    suspend fun execute(note: Note) {
        repository.addFavoriteNote(note)
    }
}