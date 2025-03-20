package com.example.togetherapp.domain.usecase.comnote

import com.example.togetherapp.domain.model.comnote.Note
import com.example.togetherapp.domain.repository.NoteRepository
import com.example.togetherapp.domain.utils.NoteTopic

class GetNotesByTopicUseCase(private val repository: NoteRepository) {
    suspend fun execute(topic: NoteTopic): List<Note> {
        return repository.getNotesByTopic(topic)
    }
}