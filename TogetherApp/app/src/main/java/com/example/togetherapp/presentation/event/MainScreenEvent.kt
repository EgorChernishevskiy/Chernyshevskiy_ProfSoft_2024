package com.example.togetherapp.presentation.event

import com.example.togetherapp.domain.utils.NoteTopic

sealed class MainScreenEvent {
    object LoadChallenges : MainScreenEvent()
    object LoadCourses : MainScreenEvent()
    object LoadNotes : MainScreenEvent()
    object LoadLocalNotes : MainScreenEvent()
    object ShowAllCourses : MainScreenEvent()
    object HideAllCourses : MainScreenEvent()
    object ShowAllNotes : MainScreenEvent()
    object HideAllNotes : MainScreenEvent()
    object NavigateToLogin : MainScreenEvent()
    object ShowAllLocalNotes : MainScreenEvent()
    object HideAllLocalNotes : MainScreenEvent()
    object OnResetState : MainScreenEvent()
    object OnErrorClear : MainScreenEvent()
    data class LoadNotesByTopic(val topic: NoteTopic) : MainScreenEvent()
    data class LoadChallengesByTopic(val topic: NoteTopic) : MainScreenEvent()
    data class UpdateTopicName(val topicName: String?) : MainScreenEvent()
}