package com.example.togetherapp.data.sse

import android.util.Log
import com.example.togetherapp.data.interceptor.TokenInterceptor
import com.example.togetherapp.domain.repository.TokenRepository
import com.example.togetherapp.domain.utils.NoteTopic
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.sse.EventSource
import okhttp3.sse.EventSourceListener
import okhttp3.sse.EventSources
import java.util.concurrent.TimeUnit

class SSEClient(
    private val tokenRepository: TokenRepository, // 👈 Добавляем TokenRepository
    private val onEvent: (String) -> Unit,
    private val onFailure: (Throwable) -> Unit
) {
    private val client = OkHttpClient.Builder()
        .readTimeout(0, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private var eventSource: EventSource? = null

    fun subscribe(topic: NoteTopic) {
        val token = tokenRepository.getToken()
        Log.d("SSE_DEBUG", "Using token: ${token?.take(10)}...") // Логируем часть токена

        val request = Request.Builder()
            .url("http://10.0.2.2:8081/chat/subscribe?technique=${topic.name}")
            .addHeader("Authorization", "Bearer $token")
            .build()

        Log.d("SSE_DEBUG", "Trying to connect to: ${request.url}")

        eventSource = EventSources.createFactory(client)
            .newEventSource(request, object : EventSourceListener() {
                override fun onOpen(eventSource: EventSource, response: Response) {
                    Log.d("SSE_DEBUG", "Connection opened: ${response.code}")
                }

                override fun onEvent(eventSource: EventSource, id: String?, type: String?, data: String) {
                    Log.d("SSE_DEBUG", "Raw event data: $data")

                    // Обрабатываем только события с именем "newMessage"
                    if (type == "newMessage") {
                        try {
                            // Удаляем префикс "data: " если он есть
                            val cleanData = data.removePrefix("data: ").trim()
                            Log.d("SSE_DEBUG", "Processing cleaned data: $cleanData")
                            onEvent(cleanData)
                        } catch (e: Exception) {
                            Log.e("SSE_DEBUG", "Error processing event: ${e.message}")
                        }
                    }
                }

                override fun onFailure(eventSource: EventSource, t: Throwable?, response: Response?) {
                    val errorMessage = buildString {
                        append("SSE Error: ")
                        append(t?.message ?: "No message")
                        append(", Response code: ${response?.code}")
                        append(", Headers: ${response?.headers}")
                    }
                    Log.e("SSE_DEBUG", errorMessage)
                    onFailure(t ?: Exception(errorMessage))
                }
            })
    }

    fun unsubscribe() {
        eventSource?.cancel()
        Log.d("SSE_DEBUG", "Unsubscribed from SSE")
    }
}