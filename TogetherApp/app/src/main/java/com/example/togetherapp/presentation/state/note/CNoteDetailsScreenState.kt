package com.example.togetherapp.presentation.state.note

import com.example.togetherapp.domain.model.comnote.Note

data class CNoteDetailsScreenState (
    override val note: Note? = null,
    override val isLoading: Boolean = false,
    override val error: String? = null,
    val commentText: String = "",
    override val isFavorite: Boolean = false
) : NoteDetailsScreenState<Note>