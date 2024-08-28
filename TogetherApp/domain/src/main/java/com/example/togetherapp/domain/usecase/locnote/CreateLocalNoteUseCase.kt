package com.example.togetherapp.domain.usecase.locnote

import com.example.togetherapp.domain.model.locnote.LocNote
import com.example.togetherapp.domain.repository.LocNoteRepository

class CreateLocalNoteUseCase(private val repository: LocNoteRepository) {
    suspend fun execute(note: LocNote) {
        repository.createLocalNote(note)
    }
}