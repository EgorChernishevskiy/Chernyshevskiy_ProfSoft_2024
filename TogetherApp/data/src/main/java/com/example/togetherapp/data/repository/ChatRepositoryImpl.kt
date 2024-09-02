package com.example.togetherapp.data.repository

import com.example.togetherapp.data.api.ChatApi
import com.example.togetherapp.data.mappers.chat.ChatMapper
import com.example.togetherapp.domain.model.chat.ChatMessage
import com.example.togetherapp.domain.repository.ChatRepository

class ChatRepositoryImpl(
    private val chatApi: ChatApi,
    private val chatMessageMapper: ChatMapper
) : ChatRepository {

    override suspend fun getAllMessages(): List<ChatMessage> {
        val response = chatApi.getAllMessages()
        if (response.isSuccessful) {
            return response.body()?.data?.map { chatMessageMapper.toDomain(it) } ?: emptyList()
        } else {
            throw Exception("Failed to fetch messages: ${response.message()}")
        }
    }

    override suspend fun sendMessage(text: String): ChatMessage {
        val response = chatApi.sendMessage(text)
        if (response.isSuccessful) {
            return response.body()?.data?.let { chatMessageMapper.toDomain(it) }
                ?: throw Exception("Failed to send message")
        } else {
            throw Exception("Failed to send message: ${response.message()}")
        }
    }
}