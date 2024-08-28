package com.example.togetherapp.domain.usecase.comnote

import com.example.togetherapp.domain.model.comnote.CreatedNote
import com.example.togetherapp.domain.model.comnote.Note
import com.example.togetherapp.domain.repository.NoteRepository

class CreateNoteUseCase(private val repository: NoteRepository) {
    suspend fun execute(note: CreatedNote): Note {
        return repository.createNote(note)
    }
}