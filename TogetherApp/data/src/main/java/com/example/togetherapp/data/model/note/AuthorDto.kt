package com.example.togetherapp.data.model.note

data class AuthorDto(
    val id: Int,
    val name: String,
    val surname: String,
    val avatar: String?,
    val email: String
)