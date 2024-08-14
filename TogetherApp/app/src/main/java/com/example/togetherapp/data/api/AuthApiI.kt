package com.example.togetherapp.data.api

import com.example.togetherapp.data.model.LoginRequest
import com.example.togetherapp.data.model.LoginResponse
import com.example.togetherapp.data.model.RegisterRequest
import com.example.togetherapp.data.model.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("auth/register")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<RegisterResponse>
}