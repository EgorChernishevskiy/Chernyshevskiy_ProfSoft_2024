package com.example.togetherapp.data.api

import com.example.togetherapp.data.model.AuthResponse
import com.example.togetherapp.data.model.LoginRequest
import com.example.togetherapp.data.model.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth")
    suspend fun login(@Body loginRequest: LoginRequest): Response<AuthResponse>

    @POST("register")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<AuthResponse>
}