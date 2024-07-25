package com.example.lesson7.di

import com.example.lesson7.data.api.WeatherApi
import com.example.lesson7.data.repository.WeatherRepositoryImpl
import com.example.lesson7.domain.repository.WeatherRepository
import com.example.lesson7.domain.usecase.GetWeatherUseCase
import com.example.lesson7.presentation.viewmodel.WeatherViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {

    single{
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    single<WeatherRepository> { WeatherRepositoryImpl(get()) }

    single { GetWeatherUseCase(get()) }

    viewModel { WeatherViewModel(get()) }
}
