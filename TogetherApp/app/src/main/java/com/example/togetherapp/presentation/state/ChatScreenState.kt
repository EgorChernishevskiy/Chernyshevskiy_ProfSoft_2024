package com.example.togetherapp.presentation.state

import com.example.togetherapp.domain.model.chat.ChatMessage

data class ChatScreenState(
    val messages: List<ChatMessage> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val currentUserId: String? = null
)