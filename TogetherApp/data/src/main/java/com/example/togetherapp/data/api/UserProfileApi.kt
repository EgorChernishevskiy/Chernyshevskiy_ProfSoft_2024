package com.example.togetherapp.data.api

import com.example.togetherapp.data.model.profile.AllProfilesResponse
import com.example.togetherapp.data.model.profile.ChangeVisibilityRequest
import com.example.togetherapp.data.model.profile.ProfilePreviewDto
import com.example.togetherapp.data.model.profile.UserProfileDto
import com.example.togetherapp.data.model.profile.UserProfileResponse
import com.example.togetherapp.domain.model.profile.UserProfile
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface UserProfileApi {
    @GET("/user/get_profile")
    suspend fun getUserProfile(): Response<UserProfileDto>

    @GET("/user/profile")
    suspend fun getUserProfileById(@Query("userId") userId: String): Response<UserProfileDto>

    @GET("/user/get_all_profiles")
    suspend fun getAllUserProfiles(): Response<List<UserProfileDto>>

    @PUT("/api/profile/phone_visibility")
    suspend fun setPhoneVisibility(@Body request: ChangeVisibilityRequest): Response<UserProfileResponse>
}