package com.example.togetherapp.domain.usecase.course

import com.example.togetherapp.domain.model.comnote.Note
import com.example.togetherapp.domain.model.course.ChallengeDto
import com.example.togetherapp.domain.repository.CourseRepository
import com.example.togetherapp.domain.utils.NoteTopic

class GetChallengesByTopicUseCase(private val repository: CourseRepository) {
    suspend fun execute(topic: NoteTopic): List<ChallengeDto> {
        return repository.getChallengesByTopic(topic)
    }
}