package com.example.togetherapp.domain.usecase.note

import com.example.togetherapp.domain.model.note.Note
import com.example.togetherapp.domain.repository.NoteRepository

class GetNotesUseCase(private val repository: NoteRepository) {
    suspend fun execute(): List<Note> {
        return repository.getNotes()
    }
}