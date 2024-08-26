package com.example.togetherapp.domain.usecase.locnote

import com.example.togetherapp.domain.model.locnote.LocNote
import com.example.togetherapp.domain.repository.LocNoteRepository

class CreateLocalNoteUseCase(private val repository: LocNoteRepository) {
    suspend operator fun invoke(note: LocNote) {
        repository.createLocalNote(note)
    }
}