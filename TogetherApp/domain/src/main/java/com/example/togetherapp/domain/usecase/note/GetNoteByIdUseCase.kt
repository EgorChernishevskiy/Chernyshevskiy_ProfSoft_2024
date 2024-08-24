package com.example.togetherapp.domain.usecase.note

import com.example.togetherapp.domain.model.note.Note
import com.example.togetherapp.domain.repository.NoteRepository

class GetNoteByIdUseCase(private val repository: NoteRepository) {
    suspend fun execute(noteId: String): Note {
        return repository.getNoteById(noteId)
    }
}