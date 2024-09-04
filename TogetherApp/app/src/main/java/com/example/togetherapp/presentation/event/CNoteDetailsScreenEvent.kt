package com.example.togetherapp.presentation.event

sealed class CNoteDetailsScreenEvent {
    data class LoadCNoteDetails(val noteId: String) : CNoteDetailsScreenEvent()
    data class UpdateCommentText(val newText: String) : CNoteDetailsScreenEvent()
    data class AddComment(val noteId: String) : CNoteDetailsScreenEvent()
    object AddToFavorite : CNoteDetailsScreenEvent()
    object RemoveFromFavorite : CNoteDetailsScreenEvent()
    data class CheckIfFavorite(val noteId: String) : CNoteDetailsScreenEvent()
    object OnErrorClear : CNoteDetailsScreenEvent()
}