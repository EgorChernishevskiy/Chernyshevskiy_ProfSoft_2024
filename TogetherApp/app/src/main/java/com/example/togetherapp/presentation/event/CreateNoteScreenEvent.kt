package com.example.togetherapp.presentation.event

import com.example.togetherapp.domain.model.comnote.CreatedNote
import com.example.togetherapp.domain.model.locnote.LocNote

sealed class CreateNoteScreenEvent {
    data class OnTitleChange(val title: String) : CreateNoteScreenEvent()
    data class OnAddItemChange(val addItem: String) : CreateNoteScreenEvent()
    object OnLocalSelected : CreateNoteScreenEvent()
    object OnCommunitySelected : CreateNoteScreenEvent()
    object OnWhatToAdd : CreateNoteScreenEvent()
    object OnShowAddText : CreateNoteScreenEvent()
    object OnShowAddPhoto : CreateNoteScreenEvent()
    object OnAddText : CreateNoteScreenEvent()
    object OnAddPhoto : CreateNoteScreenEvent()
    object OnLocalCreated : CreateNoteScreenEvent()
    object OnCommunityCreated : CreateNoteScreenEvent()

}