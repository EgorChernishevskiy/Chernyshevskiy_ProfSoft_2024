package com.example.togetherapp.data.repository

import android.util.Log
import com.example.togetherapp.data.api.UserProfileApi
import com.example.togetherapp.data.mappers.profile.UserProfileMapper
import com.example.togetherapp.data.model.profile.ChangeVisibilityRequest
import com.example.togetherapp.data.model.profile.UserProfileDto
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
            return response.body()?.let { mapper.toDomain(it) }
                ?: throw Exception("User profile not found")
        } else {
            throw Exception("Failed to fetch user profile: ${response.message()}")
        }
    }

    override suspend fun getUserProfileById(userId: String): UserProfile {
        val response = try {
            api.getUserProfileById(userId.toLong())
        } catch (e: NumberFormatException) {
            throw Exception("Invalid user ID format")
        }

        if (response.isSuccessful) {
            val responseBody = response.body()
            Log.d("UserProfileApi", "Response body: $responseBody")
            return responseBody?.let { mapper.toDomain(it) }
                ?: throw Exception("User profile not found")
        } else {
            throw Exception("Failed to fetch user profile: ${response.message()}")
        }
    }

    override suspend fun getAllUserProfiles(): List<UserProfile> {
        val response = api.getAllUserProfiles()
        if (response.isSuccessful) {
            return response.body()?.map { mapper.toDomain(it) } ?: emptyList()
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


    override suspend fun updateProfile(
        name: String?,
        surname: String?,
        email: String?,
        avatar: String?
    ): UserProfile {
        val response = api.updateProfile(
            name = if (!name.isNullOrBlank()) name else null,
            surname = if (!surname.isNullOrBlank()) surname else null,
            email = if (!email.isNullOrBlank()) email else null,
            avatar = if (!avatar.isNullOrBlank()) avatar else null
        )

        if (response.isSuccessful) {
            return response.body()?.let { mapper.toDomain(it) }
                ?: throw Exception("Failed to parse updated profile")
        } else {
            when (response.code()) {
                400 -> throw Exception("Email already taken or invalid data")
                else -> throw Exception("Failed to update profile: ${response.message()}")
            }
        }
    }
}