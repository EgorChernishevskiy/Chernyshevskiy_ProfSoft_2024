package com.example.togetherapp.presentation.state

import com.example.togetherapp.domain.model.comnote.CreatedNote
import com.example.togetherapp.domain.model.comnote.NoteContent
import com.example.togetherapp.domain.model.locnote.LocNote

data class CreateNoteScreenState(
    val isLoading: Boolean = false,
    val isLocal: Boolean = true,
    val localNote: LocNote? = null,
    val communityNote: CreatedNote? = null,
    val whatToAdd: Boolean = false,
    val addPhoto: Boolean = false,
    val addText: Boolean = false,
    val title: String = "Название",
    val noteContent: NoteContent? = null,
    val addedItem: String = "",
    val error: String? = null
)