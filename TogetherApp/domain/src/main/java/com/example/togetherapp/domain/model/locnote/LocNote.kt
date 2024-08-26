package com.example.togetherapp.domain.model.locnote

data class LocNote(
    val id: String,
    val title: String,
    val content: List<LocNoteContent>,
    val date: String
)
