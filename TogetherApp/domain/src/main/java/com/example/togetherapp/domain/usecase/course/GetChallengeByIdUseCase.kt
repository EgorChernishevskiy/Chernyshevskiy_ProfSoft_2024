package com.example.togetherapp.domain.usecase.course

import com.example.togetherapp.domain.model.course.ChallengeDto
import com.example.togetherapp.domain.repository.CourseRepository

class GetChallengeByIdUseCase(private val repository: CourseRepository) {
    suspend fun execute(id: String): ChallengeDto {
        return repository.getChallengeById(id)
    }
}