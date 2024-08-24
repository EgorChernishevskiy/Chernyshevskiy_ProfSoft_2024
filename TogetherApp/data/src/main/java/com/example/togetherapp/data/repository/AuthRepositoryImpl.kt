package com.example.togetherapp.data.repository

import com.example.togetherapp.data.api.AuthApi
import com.example.togetherapp.data.mappers.auth.AuthMapper
import com.example.togetherapp.domain.model.auth.LoginParams
import com.example.togetherapp.domain.model.auth.RegisterParams
import com.example.togetherapp.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authApiService: AuthApi,
    private val authMapper: AuthMapper
) : AuthRepository {

    override suspend fun login(params: LoginParams): Result<String> {
        val loginRequest = authMapper.toLoginRequest(params)
        return try {
            val response = authApiService.login(loginRequest)
            if (response.isSuccessful) {
                Result.success(response.body()?.data?.token ?: "")
            } else {
                Result.failure(Exception("Вход не удался"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun register(params: RegisterParams): Result<String> {
        val registerRequest = authMapper.toRegisterRequest(params)
        return try {
            val response = authApiService.register(registerRequest)
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