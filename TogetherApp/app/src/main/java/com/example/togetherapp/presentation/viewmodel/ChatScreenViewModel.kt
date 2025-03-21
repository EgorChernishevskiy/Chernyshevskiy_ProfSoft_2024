package com.example.togetherapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.togetherapp.data.model.chat.ChatMessageDto
import com.example.togetherapp.data.sse.SSEClient
import com.example.togetherapp.domain.model.chat.ChatMessage
import com.example.togetherapp.domain.usecase.chat.GetAllMessagesUseCase
import com.example.togetherapp.domain.usecase.chat.SendMessageUseCase
import com.example.togetherapp.domain.usecase.profile.GetUserProfileUseCase
import com.example.togetherapp.domain.utils.NoteTopic
import com.example.togetherapp.presentation.event.ChatScreenEvent
import com.example.togetherapp.presentation.state.ChatScreenState
import com.google.gson.Gson
import kotlinx.coroutines.launch

class ChatScreenViewModel(
    private val getAllMessagesUseCase: GetAllMessagesUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val getUserProfileUseCase: GetUserProfileUseCase
) : ViewModel() {

    private val _state = MutableLiveData(ChatScreenState())
    val state: LiveData<ChatScreenState> get() = _state

    private var currentTopic: NoteTopic = NoteTopic.OIL
    private lateinit var sseClient: SSEClient

    fun subscribeToSSE() {
        sseClient = SSEClient(
            onEvent = { eventData ->
                val newMessage = parseMessageFromEvent(eventData)
                _state.postValue(_state.value?.copy(
                    messages = _state.value?.messages.orEmpty() + newMessage
                ))
            },
            onFailure = { error ->
                _state.postValue(_state.value?.copy(error = error.message))
            }
        )
        sseClient.subscribe(currentTopic)
    }

    fun unsubscribeFromSSE() {
        sseClient.unsubscribe()
    }

    init {
        initSSEClient()
    }


    private fun initSSEClient() {
        sseClient = SSEClient(
            onEvent = { eventData ->
                // Парсим новое сообщение
                val newMessage = parseMessageFromEvent(eventData)
                // Обновляем LiveData через postValue
                _state.postValue(_state.value?.copy(
                    messages = _state.value?.messages.orEmpty() + newMessage
                ))
            },
            onFailure = { error ->
                // Обновляем LiveData через postValue
                _state.postValue(_state.value?.copy(error = error.message))
            }
        )
    }

    fun setCurrentTopic(topic: NoteTopic) {
        currentTopic = topic
        sseClient.unsubscribe() // Отписываемся от предыдущей темы
        sseClient.subscribe(topic) // Подписываемся на новую тему
        loadMessages()
    }

    fun handleEvent(event: ChatScreenEvent) {
        when (event) {
            is ChatScreenEvent.OnErrorClear -> {
                _state.value = _state.value?.copy(error = null)
            }

            is ChatScreenEvent.LoadMessages -> {
                loadMessages()
            }

            is ChatScreenEvent.SendMessage -> {
                sendMessage(event.text)
            }

            is ChatScreenEvent.RefreshMessages -> {
                loadMessages()
            }

            is ChatScreenEvent.GetCurrentUserId -> {
                getCurrentUserId()
            }
        }
    }

    private fun getCurrentUserId() {
        _state.value = _state.value?.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val user = getUserProfileUseCase.execute()
                _state.value = _state.value?.copy(currentUserId = user.id, isLoading = false)
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message, isLoading = false)
            }
        }
    }

    private fun loadMessages() {
        _state.value = _state.value?.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val messages = getAllMessagesUseCase.execute(currentTopic)
                _state.value = _state.value?.copy(messages = messages, isLoading = false)
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message, isLoading = false)
            }
        }
    }

    private fun sendMessage(text: String) {
        viewModelScope.launch {
            try {
                val newMessage = sendMessageUseCase.execute(currentTopic, text)
                val updatedMessages = _state.value?.messages.orEmpty() + newMessage
                _state.value = _state.value?.copy(
                    messages = updatedMessages,
                    currentUserId = newMessage.sender
                )
            } catch (e: Exception) {
                _state.value = _state.value?.copy(error = e.message)
            }
        }
    }

    private fun parseMessageFromEvent(eventData: String): ChatMessage {
        return try {
            Gson().fromJson(eventData, ChatMessageDto::class.java).let { dto ->
                ChatMessage(
                    id = dto.id,
                    chatRoomId = dto.chatRoomId,
                    sender = dto.sender,
                    text = dto.text,
                    timestamp = dto.timestamp
                )
            }
        } catch (e: Exception) {
            throw Exception("Failed to parse message: ${e.message}")
        }
    }

    override fun onCleared() {
        super.onCleared()
        sseClient.unsubscribe()
    }
}