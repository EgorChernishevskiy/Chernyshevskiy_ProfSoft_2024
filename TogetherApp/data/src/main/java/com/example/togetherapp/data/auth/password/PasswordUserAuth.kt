package com.example.togetherapp.data.auth.password

import com.example.togetherapp.data.api.AuthApi
import com.example.togetherapp.data.auth.UserAuth
import com.example.togetherapp.data.auth.model.LoginRequest
import com.example.togetherapp.data.auth.model.RegisterRequest

class PasswordUserAuth(private val authApiService: AuthApi) : UserAuth {
    override suspend fun login(params: LoginRequest): Result<String> {
        return try {
            val response = authApiService.login(params)
            if (response.isSuccessful) {
                Result.success(response.body()?.data?.token ?: "")
            } else {
                Result.failure(Exception("Вход не удался"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun register(params: RegisterRequest): Result<String> {
        return try {
            val response = authApiService.register(params)
            if (response.isSuccessful) {
                Result.success(response.body()?.data?.token ?: "")
            } else {
                Result.failure(Exception("Регистрация не удалась"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}