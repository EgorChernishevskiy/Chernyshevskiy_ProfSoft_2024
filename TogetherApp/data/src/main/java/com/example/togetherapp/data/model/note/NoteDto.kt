package com.example.togetherapp.data.model.note

import com.example.togetherapp.domain.utils.NoteTopic

data class NoteDto(
    val id: Int,
    val title: String,
    val topic: NoteTopic,
    val content: List<NoteContentDto>,
    val author: AuthorDto,
    val date: String,
    val comments: List<CommentDto>
)