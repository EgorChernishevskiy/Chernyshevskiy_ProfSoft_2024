package com.example.togetherapp.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.togetherapp.data.interceptor.TokenInterceptor
import com.example.togetherapp.domain.repository.TokenRepository
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://profsoft.ddns.net:8080/api/"

val networkModule = module {
    single {
        val tokenRepository: TokenRepository = get()

        OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor(tokenRepository))
            .addInterceptor(ChuckerInterceptor(get()))
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single {
        get<Retrofit>().create(com.example.togetherapp.data.api.AuthApi::class.java)
    }
    single {
        get<Retrofit>().create(com.example.togetherapp.data.api.CourseApi::class.java)
    }
    single {
        get<Retrofit>().create(com.example.togetherapp.data.api.NoteApi::class.java)
    }
    single {
        get<Retrofit>().create(com.example.togetherapp.data.api.ChatApi::class.java)
    }
    single {
        get<Retrofit>().create(com.example.togetherapp.data.api.UserProfileApi::class.java)
    }
}