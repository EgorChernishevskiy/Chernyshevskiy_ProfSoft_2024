package com.example.togetherapp.data.repository

import com.example.togetherapp.data.api.AuthApi
import com.example.togetherapp.data.auth.UserAuth
import com.example.togetherapp.data.auth.model.LoginRequest
import com.example.togetherapp.data.auth.model.RegisterRequest
import com.example.togetherapp.domain.model.LoginParams
import com.example.togetherapp.domain.model.RegisterParams
import com.example.togetherapp.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authApiService: AuthApi,
    private val userAuth: UserAuth
) : AuthRepository {
    override suspend fun login(params: LoginParams): Result<String> {
        val loginRequest = LoginRequest(
            phone = params.phone,
            passwordHashed = params.passwordHashed
        )
        return userAuth.login(loginRequest)
    }

    override suspend fun register(params: RegisterParams): Result<String> {
        val registerRequest = RegisterRequest(
            name = params.firstName,
            surname = params.lastName,
            phone = params.phoneNumber,
            passwordHashed = params.password,
            avatar = ""
        )
        return return userAuth.register(registerRequest)
    }
}
