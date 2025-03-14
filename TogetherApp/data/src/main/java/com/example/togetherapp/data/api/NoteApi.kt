package com.example.togetherapp.data.api

import com.example.togetherapp.data.model.note.CreateNoteResponse
import com.example.togetherapp.data.model.note.CreatedNoteDto
import com.example.togetherapp.data.model.note.NoteDto
import com.example.togetherapp.data.model.note.NoteListResponse
import com.example.togetherapp.data.model.note.NoteResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface NoteApi {
    @GET("note/get_all")
    suspend fun getNotes(): Response<List<NoteDto>>

    @GET("note/get/{noteId}")
    suspend fun getNoteById(@Path("noteId") noteId: Int): Response<NoteDto>

    @POST("/note/create")
    suspend fun createNote(@Body note: CreatedNoteDto): Response<NoteDto>

    @POST("/api/community_notes/comment/{noteId}")
    suspend fun addComment(
        @Path("noteId") noteId: String,
        @Body comment: Map<String, String>
    ): Response<CreateNoteResponse>
}