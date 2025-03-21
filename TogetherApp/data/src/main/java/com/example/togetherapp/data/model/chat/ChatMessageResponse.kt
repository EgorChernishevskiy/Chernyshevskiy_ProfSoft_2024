package com.example.togetherapp.data.model.chat

data class ChatMessageResponse(
    val id: Long,
    val chatRoomId: Long,
    val sender: String,
    val text: String,
    val timestamp: String
)
