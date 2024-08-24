package com.example.togetherapp.domain.usecase.note

import com.example.togetherapp.domain.model.note.Note
import com.example.togetherapp.domain.repository.NoteRepository

class CreateNoteUseCase(private val repository: NoteRepository) {
    suspend fun execute(note: Note): Note {
        return repository.createNote(note)
    }
}