package com.example.togetherapp.domain.usecase.comnote

import com.example.togetherapp.domain.model.comnote.Note
import com.example.togetherapp.domain.repository.NoteRepository

class CreateNoteUseCase(private val repository: NoteRepository) {
    suspend fun execute(note: Note): Note {
        return repository.createNote(note)
    }
}