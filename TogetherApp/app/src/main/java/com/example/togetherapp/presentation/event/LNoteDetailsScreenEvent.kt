package com.example.togetherapp.presentation.event

sealed class LNoteDetailsScreenEvent {
    data class LoadLNoteDetails(val noteId: String) : LNoteDetailsScreenEvent()
}