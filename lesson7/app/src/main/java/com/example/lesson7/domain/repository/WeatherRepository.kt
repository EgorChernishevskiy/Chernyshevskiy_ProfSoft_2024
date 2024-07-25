package com.example.lesson7.domain.repository

import com.example.lesson7.domain.model.Weather

interface WeatherRepository {
    suspend fun getWeather(city: String, apiKey: String): Weather
}