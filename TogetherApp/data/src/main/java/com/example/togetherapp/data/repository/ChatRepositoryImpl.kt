package com.example.togetherapp.data.repository

import com.example.togetherapp.data.api.ChatApi
import com.example.togetherapp.data.mappers.chat.ChatMapper
import com.example.togetherapp.data.model.chat.SendMessageRequest
import com.example.togetherapp.domain.model.chat.ChatMessage
import com.example.togetherapp.domain.repository.ChatRepository
import com.example.togetherapp.domain.utils.NoteTopic

class ChatRepositoryImpl(
    private val chatApi: ChatApi,
    private val chatMessageMapper: ChatMapper
) : ChatRepository {

    override suspend fun getAllMessages(topic: NoteTopic): List<ChatMessage> {
        val response = chatApi.getAllMessages(topic)
        if (response.isSuccessful) {
            return response.body()?.messages?.map { chatMessageMapper.toDomain(it) } ?: emptyList()
        } else {
            throw Exception("Failed to fetch messages: ${response.message()}")
        }
    }

    override suspend fun sendMessage(topic: NoteTopic, text: String): ChatMessage {
        val response = chatApi.sendMessage(SendMessageRequest(topic, text))
        if (response.isSuccessful) {
            return response.body()?.let { chatMessageMapper.toDomain(it) }
                ?: throw Exception("Failed to send message: Response body is null")
        } else {
            throw Exception("Failed to send message: ${response.message()}")
        }
    }
}