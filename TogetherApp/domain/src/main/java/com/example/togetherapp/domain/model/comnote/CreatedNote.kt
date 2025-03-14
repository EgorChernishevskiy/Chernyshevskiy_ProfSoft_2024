package com.example.togetherapp.domain.model.comnote

import com.example.togetherapp.domain.utils.NoteTopic

data class CreatedNote (
    val title: String,
    val topic: NoteTopic,
    val content: List<NoteContent>
)