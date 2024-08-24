package com.example.togetherapp.domain.usecase.note

import com.example.togetherapp.domain.model.note.Note
import com.example.togetherapp.domain.repository.NoteRepository

class AddCommentUseCase(private val repository: NoteRepository) {
    suspend fun execute(noteId: String, text: String): Note {
        return repository.addComment(noteId, text)
    }
}