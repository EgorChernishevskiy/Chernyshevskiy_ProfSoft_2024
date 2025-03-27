package com.example.togetherapp.domain.usecase.profile

import com.example.togetherapp.domain.model.profile.UserProfile
import com.example.togetherapp.domain.repository.UserProfileRepository

class UpdateProfileUseCase(
    private val repository: UserProfileRepository
) {
    suspend operator fun invoke(
        name: String? = null,
        surname: String? = null,
        email: String? = null,
        avatar: String? = null
    ): Result<UserProfile> = try {
        val updatedProfile = repository.updateProfile(
            name = name,
            surname = surname,
            email = email,
            avatar = avatar
        )
        Result.success(updatedProfile)
    } catch (e: Exception) {
        Result.failure(e)
    }
}