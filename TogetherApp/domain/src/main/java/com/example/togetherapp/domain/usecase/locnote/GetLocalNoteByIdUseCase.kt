package com.example.togetherapp.domain.usecase.locnote

import com.example.togetherapp.domain.model.locnote.LocNote
import com.example.togetherapp.domain.repository.LocNoteRepository

class GetLocalNoteByIdUseCase(private val repository: LocNoteRepository) {
    suspend fun execute(id: Int): LocNote? {
        return repository.getLocalNoteById(id)
    }
}