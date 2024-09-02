package com.example.togetherapp.domain.repository

import com.example.togetherapp.domain.model.chat.ChatMessage

interface ChatRepository {
    suspend fun getAllMessages(): List<ChatMessage>
    suspend fun sendMessage(text: String): ChatMessage
}