package com.example.togetherapp.domain.usecase.favorite

import com.example.togetherapp.domain.model.locnote.LocNote
import com.example.togetherapp.domain.repository.FavoriteRepository

class AddFavoriteLocalNoteUseCase(private val repository: FavoriteRepository) {
    suspend fun execute(note: LocNote) {
        repository.addFavoriteLocalNote(note)
    }
}