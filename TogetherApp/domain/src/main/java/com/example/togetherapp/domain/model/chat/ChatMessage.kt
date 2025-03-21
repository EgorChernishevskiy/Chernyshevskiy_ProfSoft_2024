package com.example.togetherapp.domain.model.chat

import com.example.togetherapp.domain.model.comnote.Author

data class ChatMessage(
    val id: Long,
    val chatRoomId: Long,
    val sender: String,
    val text: String,
    val timestamp: String
)