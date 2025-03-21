package com.example.togetherapp.data.sse

import com.example.togetherapp.domain.utils.NoteTopic
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.sse.EventSource
import okhttp3.sse.EventSourceListener
import okhttp3.sse.EventSources

class SSEClient(
    private val onEvent: (String) -> Unit,
    private val onFailure: (Throwable) -> Unit
) {
    private val client = OkHttpClient()
    private var eventSource: EventSource? = null

    fun subscribe(topic: NoteTopic) {
        val request = Request.Builder()
            .url("http://localhost:8081/chat/subscribe?technique=${topic.name}")
            .build()

        eventSource = EventSources.createFactory(client)
            .newEventSource(request, object : EventSourceListener() {
                override fun onEvent(eventSource: EventSource, id: String?, type: String?, data: String) {
                    onEvent(data)
                }

                override fun onFailure(eventSource: EventSource, t: Throwable?, response: Response?) {
                    onFailure(t ?: Exception("SSE connection failed"))
                }

                override fun onClosed(eventSource: EventSource) {
                    // Handle connection closed
                }
            })
    }

    fun unsubscribe() {
        eventSource?.cancel()
    }
}