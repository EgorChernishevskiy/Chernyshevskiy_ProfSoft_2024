package com.example.togetherapp.data.mappers.chat

import com.example.togetherapp.data.model.chat.ChatMessageDto
import com.example.togetherapp.data.model.note.AuthorDto
import com.example.togetherapp.domain.model.chat.ChatMessage
import com.example.togetherapp.domain.model.comnote.Author

class ChatMapperImpl : ChatMapper {
    override fun toDomain(dto: ChatMessageDto): ChatMessage {
        return ChatMessage(
            id = dto.id,
            chatRoomId = dto.chatRoomId,
            sender = dto.sender,
            text = dto.text,
            timestamp = dto.timestamp
        )
    }

    override fun toDomain(dto: AuthorDto): Author {
        return Author(
            id = dto.id.toString(),
            name = dto.name,
            surname = dto.surname,
            avatar = dto.avatar ?: "",
            email = dto.email,
            role = 0
        )
    }

    override fun toDto(domain: ChatMessage): ChatMessageDto {
        return ChatMessageDto(
            id = domain.id,
            chatRoomId = domain.chatRoomId,
            sender = domain.sender,
            text = domain.text,
            timestamp = domain.timestamp
        )
    }

    override fun toDto(domain: Author): AuthorDto {
        return AuthorDto(
            id = domain.id.toInt(),
            name = domain.name,
            surname = domain.surname,
            avatar = domain.avatar.ifEmpty { null },
            email = domain.email,
        )
    }
}