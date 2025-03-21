package com.example.togetherapp.data.api

import com.example.togetherapp.data.model.chat.ChatListResponse
import com.example.togetherapp.data.model.chat.ChatMessageDto
import com.example.togetherapp.data.model.chat.ChatMessageResponse
import com.example.togetherapp.data.model.chat.SendMessageRequest
import com.example.togetherapp.domain.utils.NoteTopic
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ChatApi {
    @GET("/chat/get")
    suspend fun getAllMessages(@Query("technique") technique: NoteTopic): Response<ChatListResponse>

    @POST("/chat/send")
    suspend fun sendMessage(@Body request: SendMessageRequest): Response<ChatMessageDto>
}