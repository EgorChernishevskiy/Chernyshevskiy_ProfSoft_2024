package com.example.lesson6.domain.repository

import com.example.lesson6.domain.model.Weather

interface WeatherRepository {
    suspend fun getWeather(city: String, apiKey: String): Weather
}