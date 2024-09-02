package com.example.togetherapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.togetherapp.domain.usecase.chat.GetAllMessagesUseCase
import com.example.togetherapp.domain.usecase.chat.SendMessageUseCase
import com.example.togetherapp.presentation.event.ChatScreenEvent
import com.example.togetherapp.presentation.state.ChatScreenState
import kotlinx.coroutines.launch

class ChatScreenViewModel(
    private val getAllMessagesUseCase: GetAllMessagesUseCase,
    private val sendMessageUseCase: SendMessageUseCase
) : ViewModel() {

    private val _state = MutableLiveData(ChatScreenState())
    val state: LiveData<ChatScreenState> get() = _state

    fun handleEvent(event: ChatScreenEvent) {
        when (event) {
            is ChatScreenEvent.LoadMessages -> {
                loadMessages()
            }

            is ChatScreenEvent.SendMessage -> {
                sendMessage(event.text)
            }

            is ChatScreenEvent.RefreshMessages -> {
                loadMessages()
            }
        }
    }

    private fun loadMessages() {
        _state.value = _state.value?.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val messages = getAllMessagesUseCase.execute()
                _state.value = _state.value?.copy(messages = messages, isLoading = false)
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message, isLoading = false)
            }
        }
    }

    private fun sendMessage(text: String) {
        viewModelScope.launch {
            try {
                val newMessage = sendMessageUseCase.execute(text)
                val updatedMessages = _state.value?.messages.orEmpty() + newMessage
                _state.value = _state.value?.copy(messages = updatedMessages, currentUserId = newMessage.author.id)
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message)
            }
        }
    }
}