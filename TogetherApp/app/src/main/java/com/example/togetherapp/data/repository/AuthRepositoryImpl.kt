package com.example.togetherapp.data.repository

import com.example.togetherapp.data.api.AuthApi
import com.example.togetherapp.data.model.LoginRequest
import com.example.togetherapp.data.model.LoginResponse
import com.example.togetherapp.data.model.RegisterRequest
import com.example.togetherapp.data.model.RegisterResponse
import com.example.togetherapp.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authApiService: AuthApi
) : AuthRepository {

    override suspend fun login(phoneNumber: String, password: String): Result<LoginResponse> {
        return try {
            val response = authApiService.login(LoginRequest(phoneNumber, password))
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Throwable(response.message()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun register(firstName: String, lastName: String, phoneNumber: String, password: String): Result<RegisterResponse> {
        return try {
            val response = authApiService.register(RegisterRequest(firstName, lastName, phoneNumber, password))
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Throwable(response.message()))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}