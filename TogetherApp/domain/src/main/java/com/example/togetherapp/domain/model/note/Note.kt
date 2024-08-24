package com.example.togetherapp.domain.model.note

data class Note(
    val id: String,
    val title: String,
    val content: List<NoteContent>,
    val author: Author,
    val date: String,
    val comments: List<Comment>
)