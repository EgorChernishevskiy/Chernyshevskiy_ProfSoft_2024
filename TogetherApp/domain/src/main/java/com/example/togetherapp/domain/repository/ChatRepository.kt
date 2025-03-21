package com.example.togetherapp.domain.repository

import com.example.togetherapp.domain.model.chat.ChatMessage
import com.example.togetherapp.domain.utils.NoteTopic

interface ChatRepository {
    suspend fun getAllMessages(topic: NoteTopic): List<ChatMessage>
    suspend fun sendMessage(topic: NoteTopic, text: String): ChatMessage
}