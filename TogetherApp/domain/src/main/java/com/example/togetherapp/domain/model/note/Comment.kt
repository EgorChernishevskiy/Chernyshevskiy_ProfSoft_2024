package com.example.togetherapp.domain.model.note

data class Comment(
    val id: String,
    val author: Author,
    val text: String
)