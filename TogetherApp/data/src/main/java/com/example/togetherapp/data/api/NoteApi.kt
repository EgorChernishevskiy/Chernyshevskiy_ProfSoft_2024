package com.example.togetherapp.data.api

import com.example.togetherapp.data.model.note.CreateNoteResponse
import com.example.togetherapp.data.model.note.CreatedNoteDto
import com.example.togetherapp.data.model.note.NoteListResponse
import com.example.togetherapp.data.model.note.NoteResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface NoteApi {
    @GET("/api/community_notes")
    suspend fun getNotes(): Response<NoteListResponse>

    @GET("/api/community_notes/{noteId}")
    suspend fun getNoteById(@Path("noteId") noteId: String): Response<NoteResponse>

    @POST("/api/community_notes")
    suspend fun createNote(@Body note: CreatedNoteDto): Response<CreateNoteResponse>

    @POST("/api/community_notes/comment/{noteId}")
    suspend fun addComment(
        @Path("noteId") noteId: String,
        @Body comment: Map<String, String>
    ): Response<CreateNoteResponse>
}