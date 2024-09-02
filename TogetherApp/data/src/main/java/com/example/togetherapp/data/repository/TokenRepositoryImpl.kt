package com.example.togetherapp.data.repository

import android.content.SharedPreferences
import com.example.togetherapp.domain.repository.TokenRepository

class TokenRepositoryImpl(private val sharedPreferences: SharedPreferences) :
    TokenRepository {

    override fun getToken(): String? {
        return sharedPreferences.getString("TOKEN_KEY", null)
    }

    override fun saveToken(token: String) {
        sharedPreferences.edit().putString("TOKEN_KEY", token).apply()
    }
}