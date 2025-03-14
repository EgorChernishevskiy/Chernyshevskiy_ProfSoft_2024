package com.example.togetherapp.data.model.note

import com.example.togetherapp.domain.utils.NoteTopic

data class CreatedNoteDto(
    val title: String,
    val topic: NoteTopic,
    val content: List<NoteContentDto>
)
