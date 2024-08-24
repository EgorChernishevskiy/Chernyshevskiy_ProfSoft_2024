package com.example.togetherapp.data.model.note

data class CommentDto(
    val id: String,
    val author: AuthorDto,
    val text: String
)