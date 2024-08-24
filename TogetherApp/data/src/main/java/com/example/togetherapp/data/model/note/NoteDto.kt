package com.example.togetherapp.data.model.note

data class NoteDto(
    val id: String,
    val title: String,
    val content: List<NoteContentDto>,
    val author: AuthorDto,
    val date: String,
    val comments: List<CommentDto>
)