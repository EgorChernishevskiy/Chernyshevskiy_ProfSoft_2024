package com.example.togetherapp.domain.model.locnote
import com.example.togetherapp.domain.model.comnote.NoteContent

data class LocNote(
    val id: Int,
    val title: String,
    val content: List<NoteContent>,
    val date: String
)

