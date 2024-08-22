package com.example.togetherapp.data.auth

import com.example.togetherapp.data.auth.model.LoginRequest
import com.example.togetherapp.data.auth.model.RegisterRequest

interface UserAuth {
    suspend fun login(params: LoginRequest): Result<String>
    suspend fun register(params: RegisterRequest): Result<String>
}