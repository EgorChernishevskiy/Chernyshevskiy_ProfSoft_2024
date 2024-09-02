package com.example.togetherapp.data.repository

import com.example.togetherapp.data.api.ChatApi
import com.example.togetherapp.data.mappers.chat.ChatMapper

class ChatRepositoryImpl(
    private val chatApi: ChatApi,
    private val chatMessageMapper: ChatMapper
) : ChatRepository {

    override suspend fun getAllMessages(): List<ChatMessage> {
        val response = chatApi.getAllMessages()
        return response.data.map { chatMessageMapper.map(it) }
    }

    override suspend fun sendMessage(text: String): ChatMessage {
        val response = chatApi.sendMessage(SendMessageRequest(text))
        return chatMessageMapper.map(response.data)
    }
}