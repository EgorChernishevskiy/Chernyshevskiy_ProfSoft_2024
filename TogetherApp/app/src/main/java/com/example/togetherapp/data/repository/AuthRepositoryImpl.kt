package com.example.togetherapp.data.repository

import com.example.togetherapp.data.api.AuthApi
import com.example.togetherapp.data.model.LoginRequest
import com.example.togetherapp.data.model.RegisterRequest
import com.example.togetherapp.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authApiService: AuthApi
) : AuthRepository {
    override suspend fun login(phone: String, passwordHashed: String): Result<String> {
        return try {
            val response = authApiService.login(LoginRequest(phone, passwordHashed))
            if (response.isSuccessful) {
                Result.success(response.body()?.token ?: "")
            } else {
                Result.failure(Exception("Login failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun register(firstName: String,  lastName: String, phoneNumber: String, password: String): Result<String> {
        return try {
            val response = authApiService.register(RegisterRequest(firstName, lastName, phoneNumber, password, ""))
            if (response.isSuccessful) {
                Result.success(response.body()?.token ?: "")
            } else {
                Result.failure(Exception("Registration failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

