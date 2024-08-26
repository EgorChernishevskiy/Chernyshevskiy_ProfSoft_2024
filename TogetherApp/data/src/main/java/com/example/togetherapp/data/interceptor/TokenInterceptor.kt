package com.example.togetherapp.data.interceptor

import com.example.togetherapp.domain.repository.TokenRepository
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(private val tokenRepository: TokenRepository) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = tokenRepository.getToken()

        val newRequest = chain.request().newBuilder().apply {
            token?.let {
                addHeader("Authorization", "Bearer $it")
            }
        }.build()

        return chain.proceed(newRequest)
    }
}