package com.example.togetherapp.domain.model.note

data class Author(
    val id: String,
    val name: String,
    val surname: String,
    val avatar: String,
    val role: Int
)