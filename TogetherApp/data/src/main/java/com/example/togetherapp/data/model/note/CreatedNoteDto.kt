package com.example.togetherapp.data.model.note

data class CreatedNoteDto(
    val title: String,
    val content: List<NoteContentDto>
)
