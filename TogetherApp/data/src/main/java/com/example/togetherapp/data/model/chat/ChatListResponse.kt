package com.example.togetherapp.data.model.chat

data class ChatListResponse (
    val id: Long,
    val technique: String,
    val name: String,
    val createdDate: String,
    val messages: List<ChatMessageDto>
)