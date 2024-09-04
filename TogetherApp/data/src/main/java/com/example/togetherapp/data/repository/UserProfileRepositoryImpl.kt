package com.example.togetherapp.data.repository

import android.util.Log
import com.example.togetherapp.data.api.UserProfileApi
import com.example.togetherapp.data.mappers.profile.UserProfileMapper
import com.example.togetherapp.data.model.profile.ChangeVisibilityRequest
import com.example.togetherapp.domain.model.profile.ProfilePreview
import com.example.togetherapp.domain.model.profile.UserProfile
import com.example.togetherapp.domain.repository.UserProfileRepository

class UserProfileRepositoryImpl(
    private val api: UserProfileApi,
    private val mapper: UserProfileMapper
) : UserProfileRepository {

    override suspend fun getUserProfile(): UserProfile {
        val response = api.getUserProfile()
        if (response.isSuccessful) {
            return response.body()?.data?.let { mapper.toDomain(it) }
                ?: throw Exception("User profile not found")
        } else {
            throw Exception("Failed to fetch user profile: ${response.message()}")
        }
    }

    override suspend fun getUserProfileById(userId: String): UserProfile {
        val response = api.getUserProfileById(userId)
        if (response.isSuccessful) {
            val responseBody = response.body()
            Log.d("UserProfileApi", "Response body: $responseBody")
            return responseBody?.let { mapper.toDomain(it) }
                ?: throw Exception("User profile not found")
        } else {
            throw Exception("Failed to fetch user profile: ${response.message()}")
        }
    }

    override suspend fun getAllUserProfiles(): List<ProfilePreview> {
        val response = api.getAllUserProfiles()
        if (response.isSuccessful) {
            return response.body()?.data?.map { mapper.toDomain(it) } ?: emptyList()
        } else {
            throw Exception("Failed to fetch user profiles: ${response.message()}")
        }
    }

    override suspend fun setPhoneVisibility(isVisible: Boolean): UserProfile {
        val response = api.setPhoneVisibility(ChangeVisibilityRequest(isVisible))
        if (response.isSuccessful) {
            return response.body()?.data?.let { mapper.toDomain(it) }
                ?: throw Exception("Failed to update phone visibility")
        } else {
            throw Exception("Failed to update phone visibility: ${response.message()}")
        }
    }
}