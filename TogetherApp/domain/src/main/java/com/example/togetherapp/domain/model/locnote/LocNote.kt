package com.example.togetherapp.domain.model.locnote

data class LocNote(
    val id: Int,
    val title: String,
    val content: List<LocNoteContent>,
    val date: String
)
