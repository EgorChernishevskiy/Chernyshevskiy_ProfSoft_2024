package com.example.togetherapp.data.api

import com.example.togetherapp.data.model.auth.AuthResponse
import com.example.togetherapp.data.model.auth.LoginRequest
import com.example.togetherapp.data.model.auth.RegisterRequest
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface AuthApi {
    @POST("user/login")
    suspend fun login(@QueryMap loginRequest: Map<String, String>): Response<ResponseBody>

    @POST("user/register")
    suspend fun register(@QueryMap registerRequest: Map<String, String>): Response<AuthResponse>
}