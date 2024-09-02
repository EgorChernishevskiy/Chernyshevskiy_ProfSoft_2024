package com.example.togetherapp.domain.usecase.chat

import com.example.togetherapp.domain.model.chat.ChatMessage
import com.example.togetherapp.domain.repository.ChatRepository

class SendMessageUseCase(private val chatRepository: ChatRepository) {
    suspend fun execute(text: String): ChatMessage = chatRepository.sendMessage(text)
}