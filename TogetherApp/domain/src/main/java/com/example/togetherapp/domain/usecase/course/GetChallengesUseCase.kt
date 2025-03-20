package com.example.togetherapp.domain.usecase.course

import com.example.togetherapp.domain.model.course.ChallengeDto
import com.example.togetherapp.domain.repository.CourseRepository

class GetChallengesUseCase(private val repository: CourseRepository) {
    suspend fun execute(): List<ChallengeDto> {
        return repository.getChallenges()
    }
}