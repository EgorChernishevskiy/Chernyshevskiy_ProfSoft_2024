package com.example.togetherapp.data.repository

import com.example.togetherapp.data.api.NoteApi
import com.example.togetherapp.data.mappers.note.NoteMapper
import com.example.togetherapp.domain.model.comnote.CreatedNote
import com.example.togetherapp.domain.model.comnote.Note
import com.example.togetherapp.domain.repository.NoteRepository

class NoteRepositoryImpl(
    private val api: NoteApi,
    private val mapper: NoteMapper
) : NoteRepository {

    override suspend fun getNotes(): List<Note> {
        val response = api.getNotes()
        if (response.isSuccessful) {
            return response.body()?.data?.map { mapper.toDomain(it) } ?: emptyList()
        } else {
            throw Exception("Failed to fetch notes: ${response.message()}")
        }
    }

    override suspend fun getNoteById(noteId: String): Note {
        val response = api.getNoteById(noteId)
        if (response.isSuccessful) {
            return response.body()?.data?.let { mapper.toDomain(it) }
                ?: throw Exception("Note not found")
        } else {
            throw Exception("Failed to fetch note: ${response.message()}")
        }
    }

    override suspend fun createNote(note: CreatedNote): Note {
        val noteDto = mapper.toDto(note)
        val response = api.createNote(noteDto)
        if (response.isSuccessful) {
            return response.body()?.data?.let { mapper.toDomain(it) }
                ?: throw Exception("Failed to create note")
        } else {
            throw Exception("Failed to create note: ${response.message()}")
        }
    }

    override suspend fun addComment(noteId: String, text: String): Note {
        val commentRequest = mapOf("text" to text)
        val response = api.addComment(noteId, commentRequest)
        if (response.isSuccessful) {
            return response.body()?.data?.let { mapper.toDomain(it) }
                ?: throw Exception("Failed to add comment")
        } else {
            throw Exception("Failed to add comment: ${response.message()}")
        }
    }
}