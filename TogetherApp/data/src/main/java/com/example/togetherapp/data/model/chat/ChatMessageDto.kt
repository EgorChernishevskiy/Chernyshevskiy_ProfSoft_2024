package com.example.togetherapp.data.model.chat

import com.example.togetherapp.data.model.note.AuthorDto

data class ChatMessageDto(
    val id: String,
    val date: String,
    val author: AuthorDto,
    val message: String
)