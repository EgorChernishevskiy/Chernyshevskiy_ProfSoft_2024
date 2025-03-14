package com.example.togetherapp.presentation.utils

import com.example.togetherapp.domain.utils.NoteTopic

fun getTopicName(topic: NoteTopic): String {
    return when (topic) {
        NoteTopic.OIL -> "Масло"
        NoteTopic.WATERCOLOR -> "Акварель"
        NoteTopic.PASTEL -> "Пастель"
        NoteTopic.CHARCOAL -> "Уголь"
        NoteTopic.PENCIL -> "Карандаш"
    }
}