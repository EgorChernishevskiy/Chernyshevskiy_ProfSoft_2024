package com.example.togetherapp.domain.usecase.comnote

import com.example.togetherapp.domain.model.comnote.Note
import com.example.togetherapp.domain.repository.NoteRepository

class GetNotesUseCase(private val repository: NoteRepository) {
    suspend fun execute(): List<Note> {
        return repository.getNotes()
    }
}