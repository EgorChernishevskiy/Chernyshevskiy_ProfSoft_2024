package com.example.togetherapp.data.mappers.chat

import com.example.togetherapp.data.model.chat.ChatMessageDto
import com.example.togetherapp.data.model.note.AuthorDto
import com.example.togetherapp.domain.model.chat.ChatMessage
import com.example.togetherapp.domain.model.comnote.Author

interface ChatMapper {
    fun toDomain(dto: ChatMessageDto): ChatMessage
    fun toDto(domain: ChatMessage): ChatMessageDto
    fun toDomain(dto: AuthorDto): Author
    fun toDto(domain: Author): AuthorDto
}