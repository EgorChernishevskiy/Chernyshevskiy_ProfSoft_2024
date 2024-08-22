package com.example.togetherapp.domain.repository

import com.example.togetherapp.domain.model.LoginParams
import com.example.togetherapp.domain.model.RegisterParams

interface AuthRepository {
    suspend fun login(params: LoginParams): Result<String>
    suspend fun register(params: RegisterParams): Result<String>
}
