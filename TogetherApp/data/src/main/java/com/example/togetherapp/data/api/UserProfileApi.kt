package com.example.togetherapp.data.api

import com.example.togetherapp.data.model.profile.AllProfilesResponse
import com.example.togetherapp.data.model.profile.UserProfileResponse
import retrofit2.Response

interface UserProfileApi {
    suspend fun getUserProfile(): Response<UserProfileResponse>
    suspend fun getUserProfileById(userId: String): Response<UserProfileResponse>
    suspend fun getAllUserProfiles(): Response<AllProfilesResponse>
    suspend fun setPhoneVisibility(isVisible: Boolean): Response<UserProfileResponse>
}