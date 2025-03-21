package com.example.togetherapp.domain.usecase.chat

import com.example.togetherapp.domain.model.chat.ChatMessage
import com.example.togetherapp.domain.repository.ChatRepository
import com.example.togetherapp.domain.utils.NoteTopic

class SendMessageUseCase(private val chatRepository: ChatRepository) {
    suspend fun execute(topic: NoteTopic, text: String): ChatMessage = chatRepository.sendMessage(topic, text)
}