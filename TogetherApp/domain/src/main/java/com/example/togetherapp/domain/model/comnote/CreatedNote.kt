package com.example.togetherapp.domain.model.comnote

data class CreatedNote (
    val title: String,
    val content: List<NoteContent>
)