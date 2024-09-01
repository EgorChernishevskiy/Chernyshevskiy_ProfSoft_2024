package com.example.togetherapp.presentation.state.note

import com.example.togetherapp.domain.model.locnote.LocNote

data class LNoteDetailsScreenState (
    override val note: LocNote? = null,
    override val isLoading: Boolean = false,
    override val error: String? = null,
    override val isFavorite: Boolean = false
) : NoteDetailsScreenState<LocNote>