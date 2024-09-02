package com.example.togetherapp.data.api

import com.example.togetherapp.data.model.chat.ChatMessageDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ChatApi {
    @GET("/api/chat")
    suspend fun getAllMessages(): Response<List<ChatMessageDto>>

    @POST("/api/chat")
        suspend fun sendMessage(@Body text: String): Response<ChatMessageDto>
}