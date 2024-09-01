package com.example.togetherapp.presentation.event

sealed class CreateNoteScreenEvent {
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
}