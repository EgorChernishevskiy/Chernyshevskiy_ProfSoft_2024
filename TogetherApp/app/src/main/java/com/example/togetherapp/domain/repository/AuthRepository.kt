package com.example.togetherapp.domain.repository


interface AuthRepository {
    suspend fun login(phone: String, passwordHashed: String): Result<String>
    suspend fun register(firstName: String, lastName: String, phoneNumber: String, password: String): Result<String>
}
