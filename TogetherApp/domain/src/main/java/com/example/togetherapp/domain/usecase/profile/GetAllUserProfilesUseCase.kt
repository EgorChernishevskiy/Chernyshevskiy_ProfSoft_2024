package com.example.togetherapp.domain.usecase.profile

import com.example.togetherapp.domain.model.profile.UserProfile
import com.example.togetherapp.domain.repository.UserProfileRepository

class GetAllUserProfilesUseCase(private val repository: UserProfileRepository) {
    suspend fun execute(): List<UserProfile> {
        return repository.getAllUserProfiles()
    }
}