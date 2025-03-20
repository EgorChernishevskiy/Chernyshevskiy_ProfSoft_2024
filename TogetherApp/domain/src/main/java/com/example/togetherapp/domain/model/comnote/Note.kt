package com.example.togetherapp.domain.model.comnote

import com.example.togetherapp.domain.utils.NoteTopic

data class Note (
    val id: String,
    val title: String,
    val topic: NoteTopic,
    val content: List<NoteContent>,
    val author: Author,
    val date: String,
    val comments: List<Comment>
)