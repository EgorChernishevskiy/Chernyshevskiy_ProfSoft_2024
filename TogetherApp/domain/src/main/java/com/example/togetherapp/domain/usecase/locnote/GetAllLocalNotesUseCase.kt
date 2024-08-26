package com.example.togetherapp.domain.usecase.locnote

import com.example.togetherapp.domain.model.locnote.LocNote
import com.example.togetherapp.domain.repository.LocNoteRepository

class GetAllLocalNotesUseCase(private val repository: LocNoteRepository) {
    suspend fun execute(): List<LocNote> {
        return repository.getAllLocalNotes()
    }
}