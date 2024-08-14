package com.example.togetherapp.domain.repository

import com.example.togetherapp.data.model.LoginResponse
import com.example.togetherapp.data.model.RegisterResponse

interface AuthRepository {
    suspend fun login(phoneNumber: String, password: String): Result<LoginResponse>
    suspend fun register(firstName: String, lastName: String, phoneNumber: String, password: String): Result<RegisterResponse>
}