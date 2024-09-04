package com.example.togetherapp.presentation.event

sealed class ChatScreenEvent {
    object LoadMessages : ChatScreenEvent()
    data class SendMessage(val text: String) : ChatScreenEvent()
    object RefreshMessages : ChatScreenEvent()
    object GetCurrentUserId : ChatScreenEvent()
}