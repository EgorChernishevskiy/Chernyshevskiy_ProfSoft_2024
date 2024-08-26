package com.example.togetherapp.presentation.state

import com.example.togetherapp.domain.model.comnote.Note

data class CNoteDetailsScreenState (
    val note: Note? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val commentText: String = ""
)