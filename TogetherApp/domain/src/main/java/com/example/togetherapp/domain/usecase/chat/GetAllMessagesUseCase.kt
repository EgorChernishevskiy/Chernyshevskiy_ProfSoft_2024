package com.example.togetherapp.domain.usecase.chat

import com.example.togetherapp.domain.model.chat.ChatMessage
import com.example.togetherapp.domain.repository.ChatRepository
import com.example.togetherapp.domain.utils.NoteTopic

class GetAllMessagesUseCase(private val chatRepository: ChatRepository) {
    suspend fun execute(topic: NoteTopic): List<ChatMessage> = chatRepository.getAllMessages(topic)
}