package com.example.togetherapp.presentation.event

sealed class LNoteDetailsScreenEvent {
    data class LoadLNoteDetails(val noteId: Int) : LNoteDetailsScreenEvent()
    object AddToFavorite : LNoteDetailsScreenEvent()
    object RemoveFromFavorite : LNoteDetailsScreenEvent()
    data class CheckIfFavorite(val noteId: Int) : LNoteDetailsScreenEvent()
}