package com.example.togetherapp.presentation.viewmodel

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.togetherapp.data.model.chat.ChatMessageDto
import com.example.togetherapp.data.sse.SSEClient
import com.example.togetherapp.domain.model.chat.ChatMessage
import com.example.togetherapp.domain.repository.TokenRepository
import com.example.togetherapp.domain.usecase.chat.GetAllMessagesUseCase
import com.example.togetherapp.domain.usecase.chat.SendMessageUseCase
import com.example.togetherapp.domain.usecase.profile.GetUserProfileUseCase
import com.example.togetherapp.domain.utils.NoteTopic
import com.example.togetherapp.presentation.event.ChatScreenEvent
import com.example.togetherapp.presentation.state.ChatScreenState
import com.google.gson.Gson
import kotlinx.coroutines.launch
import java.io.EOFException

class ChatScreenViewModel(
    private val getAllMessagesUseCase: GetAllMessagesUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val tokenRepository: TokenRepository
) : ViewModel() {

    private val _state = MutableLiveData(ChatScreenState())
    val state: LiveData<ChatScreenState> get() = _state

    private var currentTopic: NoteTopic = NoteTopic.OIL
    private lateinit var sseClient: SSEClient
    private val reconnectHandler = Handler(Looper.getMainLooper())

    init {
        initSSEClient()
    }

    private fun initSSEClient() {
        sseClient = SSEClient(
            tokenRepository = tokenRepository, // 👈 Передаем в SSEClient
            onEvent = { eventData ->
                Log.d("SSE_DEBUG", "Received event: $eventData")
                try {
                    val newMessage = parseMessageFromEvent(eventData)
                    _state.postValue(_state.value?.copy(
                        messages = _state.value?.messages.orEmpty() + newMessage
                    ))
                } catch (e: Exception) {
                    Log.e("SSE_DEBUG", "Error processing event: ${e.stackTraceToString()}")
                }
            },
            onFailure = { error ->
                Log.e("SSE_DEBUG", "SSE failure: ${error.message}")
                reconnectHandler.postDelayed({
                    Log.d("SSE_DEBUG", "Attempting reconnect...")
                    subscribeToSSE()
                }, 5000)
            }
        )
    }

    fun setCurrentTopic(topic: NoteTopic) {
        Log.d("SSE_DEBUG", "Switching to topic: $topic")
        currentTopic = topic
        sseClient.unsubscribe()
        sseClient.subscribe(topic)
        loadMessages()
    }

    fun subscribeToSSE() {
        Log.d("SSE", "Подписка на SSE для темы: $currentTopic")
        sseClient.subscribe(currentTopic)
    }

    fun unsubscribeFromSSE() {
        Log.d("SSE", "Отписка от SSE")
        sseClient.unsubscribe()
    }

    fun handleEvent(event: ChatScreenEvent) {
        when (event) {
            is ChatScreenEvent.OnErrorClear -> {
                _state.value = _state.value?.copy(error = null)
            }

            is ChatScreenEvent.LoadMessages -> {
                if (state.value?.currentUserId == null) {
                    getCurrentUserId { loadMessages() } // Загружаем ID перед сообщениями
                } else {
                    loadMessages()
                }
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

    private fun getCurrentUserId(onSuccess: (() -> Unit)? = null) {
        _state.value = _state.value?.copy(isLoading = true)
        viewModelScope.launch {
            try {
                val user = getUserProfileUseCase.execute()
                _state.value = _state.value?.copy(currentUserId = user.name, isLoading = false)
                onSuccess?.invoke() // Загружаем сообщения только после получения ID
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
            Log.d("SSE_DEBUG", "Parsing JSON: $eventData")
            Gson().fromJson(eventData, ChatMessageDto::class.java).let { dto ->
                // Убедитесь, что sender - это строка, а не объект
                ChatMessage(
                    id = dto.id,
                    chatRoomId = dto.chatRoomId,
                    sender = dto.sender,
                    text = dto.text,
                    timestamp = dto.timestamp
                )
            }
        } catch (e: Exception) {
            Log.e("SSE_DEBUG", "Parse error: ${e.stackTraceToString()}")
            throw e
        }
    }

    override fun onCleared() {
        super.onCleared()
        sseClient.unsubscribe()
    }
}