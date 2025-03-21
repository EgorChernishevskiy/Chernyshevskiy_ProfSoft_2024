package com.example.togetherapp.data.model.chat

import com.example.togetherapp.data.model.note.AuthorDto

data class ChatMessageDto(
    val id: Long,
    val chatRoomId: Long,
    val sender: String,
    val text: String,
    val timestamp: String
)