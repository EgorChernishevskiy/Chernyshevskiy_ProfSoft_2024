package com.example.togetherapp.data.mappers.note

import com.example.togetherapp.data.model.note.AuthorDto
import com.example.togetherapp.data.model.note.CommentDto
import com.example.togetherapp.data.model.note.CreatedNoteDto
import com.example.togetherapp.data.model.note.NoteContentDto
import com.example.togetherapp.data.model.note.NoteDto
import com.example.togetherapp.domain.model.comnote.Author
import com.example.togetherapp.domain.model.comnote.Comment
import com.example.togetherapp.domain.model.comnote.CreatedNote
import com.example.togetherapp.domain.model.comnote.Note
import com.example.togetherapp.domain.model.comnote.NoteContent

class NoteMapperImpl : NoteMapper {
    override fun toDomain(dto: NoteDto): Note {
        return Note(
            id = dto.id,
            title = dto.title,
            content = dto.content.map { toDomain(it) },
            author = toDomain(dto.author),
            date = dto.date,
            comments = dto.comments.map { toDomain(it) }
        )
    }

    override fun toDto(domain: Note): NoteDto {
        return NoteDto(
            id = domain.id,
            title = domain.title,
            content = domain.content.map { toDto(it) },
            author = toDto(domain.author),
            date = domain.date,
            comments = domain.comments.map { toDto(it) }
        )
    }

    override fun toDomain(dto: NoteContentDto): NoteContent {
        return NoteContent(
            text = dto.text,
            image = dto.image
        )
    }

    override fun toDto(domain: NoteContent): NoteContentDto {
        return NoteContentDto(
            text = domain.text,
            image = domain.image
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

    override fun toDto(domain: Author): AuthorDto {
        return AuthorDto(
            id = domain.id,
            name = domain.name,
            surname = domain.surname,
            avatar = domain.avatar,
            role = domain.role
        )
    }

    override fun toDomain(dto: CommentDto): Comment {
        return Comment(
            id = dto.id,
            author = toDomain(dto.author),
            text = dto.text
        )
    }

    override fun toDto(domain: Comment): CommentDto {
        return CommentDto(
            id = domain.id,
            author = toDto(domain.author),
            text = domain.text
        )
    }

    override fun toDomain(dto: CreatedNoteDto): CreatedNote {
        return CreatedNote(
            title = dto.title ,
            content  =  dto.content.map { toDomain(it) }
        )
    }

    override fun toDto(domain: CreatedNote): CreatedNoteDto {
        return CreatedNoteDto(
            title = domain.title ,
            content  =  domain.content.map { toDto(it) }
        )
    }
}