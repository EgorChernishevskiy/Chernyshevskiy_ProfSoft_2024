package com.example.togetherapp.data.api

import com.example.togetherapp.data.model.note.CommentDto
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
import retrofit2.http.Query

interface NoteApi {
    @GET("note/get_all")
    suspend fun getNotes(): Response<List<NoteDto>>

    @GET("note/get_by_topic")
    suspend fun getNotesByTopic(@Query("topic") topic: String): Response<List<NoteDto>>

    @GET("note/get")
    suspend fun getNoteById(@Query("noteId") noteId: Int): Response<NoteDto>

    @POST("/note/create")
    suspend fun createNote(@Body note: CreatedNoteDto): Response<NoteDto>

    @POST("note/add_comment")
    suspend fun addComment(
        @Query("noteId") noteId: String,
        @Query("text") text: String
    ): Response<CommentDto>
}