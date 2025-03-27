package com.example.togetherapp.data.database.converter

import androidx.room.TypeConverter
import com.example.togetherapp.domain.utils.NoteTopic

class NoteTopicConverter {
    @TypeConverter
    fun fromTopic(topic: NoteTopic): String = topic.name

    @TypeConverter
    fun toTopic(name: String): NoteTopic = NoteTopic.valueOf(name)
}