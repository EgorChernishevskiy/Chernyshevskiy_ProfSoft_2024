package com.example.togetherapp.domain.model.chat

import com.example.togetherapp.domain.model.comnote.Author

data class ChatMessage(
    val id: String,
    val date: String,
    val author: Author,
    val message: String
)