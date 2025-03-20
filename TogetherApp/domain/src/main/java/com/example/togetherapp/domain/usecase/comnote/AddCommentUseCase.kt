package com.example.togetherapp.domain.usecase.comnote

import com.example.togetherapp.domain.model.comnote.Comment
import com.example.togetherapp.domain.model.comnote.Note
import com.example.togetherapp.domain.repository.NoteRepository

class AddCommentUseCase(private val repository: NoteRepository) {
    suspend fun execute(noteId: String, text: String): Comment {
        return repository.addComment(noteId, text)
    }
}