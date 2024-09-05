package com.example.togetherapp.data.api

import com.example.togetherapp.data.model.chat.ChatListResponse
import com.example.togetherapp.data.model.chat.ChatMessageResponse
import com.example.togetherapp.data.model.chat.SendMessageRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ChatApi {
    @GET("/api/chat")
    suspend fun getAllMessages(): Response<ChatListResponse>

    @POST("/api/chat")
    suspend fun sendMessage(@Body request: SendMessageRequest): Response<ChatMessageResponse>
}