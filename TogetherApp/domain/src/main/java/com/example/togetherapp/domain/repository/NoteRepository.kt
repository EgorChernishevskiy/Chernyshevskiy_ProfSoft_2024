package com.example.togetherapp.domain.repository

import com.example.togetherapp.domain.model.comnote.Note

interface NoteRepository {
    suspend fun getNotes(): List<Note>
    suspend fun getNoteById(noteId: String): Note
    suspend fun createNote(note: Note): Note
    suspend fun addComment(noteId: String, text: String): Note
}