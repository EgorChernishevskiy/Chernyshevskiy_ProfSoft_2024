package com.example.togetherapp.domain.usecase.course

import com.example.togetherapp.domain.model.course.ChallengeSubmissionDto
import com.example.togetherapp.domain.repository.CourseRepository

class SubmitChallengeAttemptUseCase(private val repository: CourseRepository) {
    suspend fun execute(challengeId: Long, image: String, text: String): ChallengeSubmissionDto {
        return repository.submitChallengeAttempt(challengeId, image, text)
    }
}