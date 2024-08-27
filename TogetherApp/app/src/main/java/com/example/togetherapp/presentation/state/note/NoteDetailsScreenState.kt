package com.example.togetherapp.presentation.state.note

interface NoteDetailsScreenState<T> {
    val note: T?
    val isLoading: Boolean
    val error: String?
}