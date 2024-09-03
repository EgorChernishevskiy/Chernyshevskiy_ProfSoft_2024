package com.example.togetherapp.domain.usecase.profile

import com.example.togetherapp.domain.model.profile.UserProfile
import com.example.togetherapp.domain.repository.UserProfileRepository

class GetUserProfileByIdUseCase(private val repository: UserProfileRepository) {
    suspend fun execute(userId: String): UserProfile {
        return repository.getUserProfileById(userId)
    }
}