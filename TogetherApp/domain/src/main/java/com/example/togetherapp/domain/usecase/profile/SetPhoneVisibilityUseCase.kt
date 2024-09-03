package com.example.togetherapp.domain.usecase.profile

import com.example.togetherapp.domain.model.profile.UserProfile
import com.example.togetherapp.domain.repository.UserProfileRepository

class SetPhoneVisibilityUseCase(private val repository: UserProfileRepository) {
    suspend fun execute(isVisible: Boolean): UserProfile {
        return repository.setPhoneVisibility(isVisible)
    }
}