package com.example.togetherapp.domain.usecase.comnote

import com.example.togetherapp.domain.model.comnote.Note
import com.example.togetherapp.domain.repository.NoteRepository

class GetNoteByIdUseCase(private val repository: NoteRepository) {
    suspend fun execute(noteId: String): Note {
        return repository.getNoteById(noteId)
    }
}