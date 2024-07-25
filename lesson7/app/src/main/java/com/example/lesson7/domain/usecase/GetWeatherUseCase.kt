package com.example.lesson7.domain.usecase

import com.example.lesson7.domain.model.Weather
import com.example.lesson7.domain.repository.WeatherRepository

class GetWeatherUseCase(private val repository: WeatherRepository) {
    suspend fun execute(city: String, apiKey: String): Weather {
        return repository.getWeather(city, apiKey)
    }
}