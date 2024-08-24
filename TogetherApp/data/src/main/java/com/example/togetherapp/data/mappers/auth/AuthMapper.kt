package com.example.togetherapp.data.mappers.auth

import com.example.togetherapp.data.model.auth.LoginRequest
import com.example.togetherapp.data.model.auth.RegisterRequest
import com.example.togetherapp.domain.model.auth.LoginParams
import com.example.togetherapp.domain.model.auth.RegisterParams

interface AuthMapper {
    fun toLoginRequest(params: LoginParams): LoginRequest
    fun toRegisterRequest(params: RegisterParams): RegisterRequest
}