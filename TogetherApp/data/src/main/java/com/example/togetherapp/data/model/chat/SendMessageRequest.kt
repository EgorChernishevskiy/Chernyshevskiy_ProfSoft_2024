package com.example.togetherapp.data.model.chat

import com.example.togetherapp.domain.utils.NoteTopic

data class SendMessageRequest(
    val technique: NoteTopic,
    val text: String
)