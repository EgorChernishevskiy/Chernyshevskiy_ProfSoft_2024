package com.example.togetherapp.data.mappers.auth

import com.example.togetherapp.data.model.auth.LoginRequest
import com.example.togetherapp.data.model.auth.RegisterRequest
import com.example.togetherapp.domain.model.auth.LoginParams
import com.example.togetherapp.domain.model.auth.RegisterParams

class AuthMapperImpl : AuthMapper {

    override fun toLoginRequest(params: LoginParams): LoginRequest {
        return LoginRequest(
            phone = params.phone,
            passwordHashed = params.passwordHashed
        )
    }

    override fun toRegisterRequest(params: RegisterParams): RegisterRequest {
        return RegisterRequest(
            name = params.firstName,
            surname = params.lastName,
            phone = params.phoneNumber,
            passwordHashed = params.password,
            avatar = ""
        )
    }
}