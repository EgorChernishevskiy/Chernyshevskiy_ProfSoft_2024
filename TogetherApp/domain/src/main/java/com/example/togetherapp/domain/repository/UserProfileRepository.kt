package com.example.togetherapp.domain.repository

import com.example.togetherapp.domain.model.profile.ProfilePreview
import com.example.togetherapp.domain.model.profile.UserProfile

interface UserProfileRepository {
    suspend fun getUserProfile(): UserProfile
    suspend fun getUserProfileById(userId: String): UserProfile
    suspend fun getAllUserProfiles(): List<ProfilePreview>
    suspend fun setPhoneVisibility(isVisible: Boolean): UserProfile
}