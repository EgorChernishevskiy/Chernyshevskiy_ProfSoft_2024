package com.example.lesson7.data.repository

import com.example.lesson7.data.api.WeatherApi
import com.example.lesson7.domain.model.Weather
import com.example.lesson7.domain.repository.WeatherRepository

class WeatherRepositoryImpl(private val api: WeatherApi) : WeatherRepository {

    override suspend fun getWeather(city: String, apiKey: String): Weather {
        val response = api.getWeather(city, apiKey)
        return Weather(
            cityName = response.name,
            temperature = response.main.temp,
            feelsLike = response.main.feels_like,
            tempMin = response.main.temp_min,
            tempMax = response.main.temp_max,
            pressure = response.main.pressure,
            humidity = response.main.humidity,
            visibility = response.visibility,
            windSpeed = response.wind.speed,
            windGust = response.wind.gust,
            windDirection = getWindDirection(response.wind.deg),
            description = response.weather[0].description
        )
    }

    private fun getWindDirection(deg: Int): String {
        return when (deg) {
            in 0..44 -> "N"
            in 45..89 -> "NE"
            in 90..134 -> "E"
            in 135..179 -> "SE"
            in 180..224 -> "S"
            in 225..269 -> "SW"
            in 270..314 -> "W"
            in 315..359 -> "NW"
            else -> "N/A"
        }
    }
}
