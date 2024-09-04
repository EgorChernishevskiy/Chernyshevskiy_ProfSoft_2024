package com.example.togetherapp.presentation.event

sealed class FavoriteScreenEvent {
    object LoadCourses : FavoriteScreenEvent()
    object LoadNotes : FavoriteScreenEvent()
    object LoadLocalNotes : FavoriteScreenEvent()
    object ShowAllCourses : FavoriteScreenEvent()
    object HideAllCourses : FavoriteScreenEvent()
    object ShowAllNotes : FavoriteScreenEvent()
    object HideAllNotes : FavoriteScreenEvent()
    object ShowAllLocalNotes : FavoriteScreenEvent()
    object HideAllLocalNotes : FavoriteScreenEvent()
    object OnErrorClear : FavoriteScreenEvent()
}