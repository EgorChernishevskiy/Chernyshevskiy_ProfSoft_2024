package com.example.togetherapp.presentation.event

import com.example.togetherapp.domain.utils.NoteTopic

sealed class CreateNoteScreenEvent {
    data class OnTopicSelected(val topic: NoteTopic) : CreateNoteScreenEvent()
    data class OnTitleChange(val title: String) : CreateNoteScreenEvent()
    data class OnAddItemChange(val addItem: String) : CreateNoteScreenEvent()
    object OnLocalSelected : CreateNoteScreenEvent()
    object OnCommunitySelected : CreateNoteScreenEvent()
    object OnWhatToAdd : CreateNoteScreenEvent()
    object OnDismissWhatToAdd : CreateNoteScreenEvent()
    object OnShowAddText : CreateNoteScreenEvent()
    object OnShowAddPhoto : CreateNoteScreenEvent()
    object OnDismissAddItem : CreateNoteScreenEvent()
    object OnAddText : CreateNoteScreenEvent()
    object OnAddPhoto : CreateNoteScreenEvent()
    object OnLocalCreated : CreateNoteScreenEvent()
    object OnCommunityCreated : CreateNoteScreenEvent()
    object OnResetState : CreateNoteScreenEvent()
    object OnErrorClear : CreateNoteScreenEvent()
}