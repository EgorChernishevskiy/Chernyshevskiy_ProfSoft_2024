package com.example.togetherapp.domain.usecase.chat

import com.example.togetherapp.domain.model.chat.ChatMessage
import com.example.togetherapp.domain.repository.ChatRepository

class GetAllMessagesUseCase(private val chatRepository: ChatRepository) {
    suspend fun execute(): List<ChatMessage> = chatRepository.getAllMessages()
}