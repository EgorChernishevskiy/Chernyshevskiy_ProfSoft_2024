package com.example.togetherapp.domain.repository

import com.example.togetherapp.domain.model.comnote.Comment
import com.example.togetherapp.domain.model.comnote.CreatedNote
import com.example.togetherapp.domain.model.comnote.Note
import com.example.togetherapp.domain.utils.NoteTopic

interface NoteRepository {
    suspend fun getNotes(): List<Note>
    suspend fun getNoteById(noteId: String): Note
    suspend fun createNote(note: CreatedNote): Note
    suspend fun addComment(noteId: String, text: String): Comment
    suspend fun getNotesByTopic(topic: NoteTopic): List<Note>
}