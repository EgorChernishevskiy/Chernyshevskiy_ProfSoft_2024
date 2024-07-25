package com.example.lesson7.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lesson7.domain.model.Weather
import com.example.lesson7.domain.usecase.GetWeatherUseCase
import kotlinx.coroutines.launch

class WeatherViewModel(private val getWeatherUseCase: GetWeatherUseCase) : ViewModel() {

    private val _weather = MutableLiveData<Weather>()
    val weather: LiveData<Weather> get() = _weather

    fun loadWeather(city: String, apiKey: String) {
        viewModelScope.launch {
            val weather = getWeatherUseCase.execute(city, apiKey)
            _weather.postValue(weather)
        }
    }
}