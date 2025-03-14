package com.example.togetherapp.presentation.state

import com.example.togetherapp.domain.model.comnote.CreatedNote
import com.example.togetherapp.domain.model.comnote.NoteContent
import com.example.togetherapp.domain.model.locnote.LocNote
import com.example.togetherapp.domain.utils.NoteTopic
import com.example.togetherapp.presentation.utils.getTopicName

data class CreateNoteScreenState(
    val isLoading: Boolean = false,
    val isLocal: Boolean = true,
    val localNote: LocNote? = null,
    val communityNote: CreatedNote? = null,
    val whatToAdd: Boolean = false,
    val addPhoto: Boolean = false,
    val addText: Boolean = false,
    val title: String = "Название",
    val topic: NoteTopic = NoteTopic.OIL, // Значение из enum
    val topicName: String = getTopicName(NoteTopic.OIL), // Название на русском
    val noteContent: NoteContent? = null,
    val addedItem: String = "",
    val error: String? = null
)