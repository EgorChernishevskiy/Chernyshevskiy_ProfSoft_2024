package com.example.togetherapp.presentation.event

sealed class ProfileScreenEvent {
    object ShowAllCourses : ProfileScreenEvent()
    object HideAllCourses : ProfileScreenEvent()
    object ShowAllNotes : ProfileScreenEvent()
    object HideAllNotes : ProfileScreenEvent()
    data class OnLoadUserProfileById(val id: String) : ProfileScreenEvent()
    object OnShowNumber : ProfileScreenEvent()
    object OnChangeNumberVisibility : ProfileScreenEvent()
    object ShowAllUsers : ProfileScreenEvent()
    object OnLoadUserProfile : ProfileScreenEvent()

}