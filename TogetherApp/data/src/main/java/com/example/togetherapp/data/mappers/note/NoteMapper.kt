package com.example.togetherapp.data.mappers.note

import com.example.togetherapp.data.model.note.AuthorDto
import com.example.togetherapp.data.model.note.CommentDto
import com.example.togetherapp.data.model.note.NoteContentDto
import com.example.togetherapp.data.model.note.NoteDto
import com.example.togetherapp.domain.model.note.Author
import com.example.togetherapp.domain.model.note.Comment
import com.example.togetherapp.domain.model.note.Note
import com.example.togetherapp.domain.model.note.NoteContent

interface NoteMapper {
    fun toDomain(dto: NoteDto): Note
    fun toDto(domain: Note): NoteDto
    fun toDomain(dto: NoteContentDto): NoteContent
    fun toDto(domain: NoteContent): NoteContentDto
    fun toDomain(dto: AuthorDto): Author
    fun toDto(domain: Author): AuthorDto
    fun toDomain(dto: CommentDto): Comment
    fun toDto(domain: Comment): CommentDto
}