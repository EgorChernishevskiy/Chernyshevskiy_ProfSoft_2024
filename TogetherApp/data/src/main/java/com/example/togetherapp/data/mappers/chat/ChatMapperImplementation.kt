package com.example.togetherapp.data.mappers.chat

import com.example.togetherapp.data.model.chat.ChatMessageDto
import com.example.togetherapp.data.model.note.AuthorDto
import com.example.togetherapp.domain.model.chat.ChatMessage
import com.example.togetherapp.domain.model.comnote.Author

class ChatMapperImplementation : ChatMapper {
    override fun toDomain(dto: ChatMessageDto): ChatMessage {
        return ChatMessage(
            id = dto.id,
            date = dto.date,
            author = toDomain(dto.author),
            message = dto.message
        )
    }

    override fun toDomain(dto: AuthorDto): Author {
        return Author(
            id = dto.id,
            name = dto.name,
            surname = dto.surname,
            avatar = dto.avatar,
            role = dto.role
        )
    }

    override fun toDto(domain: ChatMessage): ChatMessageDto {
        return ChatMessageDto(
            id = domain.id,
            date = domain.date,
            author = toDto(domain.author),
            message = domain.message
        )
    }

    override fun toDto(domain: Author): AuthorDto {
        return AuthorDto(
            id = domain.id,
            name = domain.name,
            surname = domain.surname,
            avatar = domain.avatar,
            role = domain.role
        )
    }
}