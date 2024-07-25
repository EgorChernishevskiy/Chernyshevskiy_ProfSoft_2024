package com.example.lesson7.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.lesson7.databinding.ActivityMainBinding
import com.example.lesson7.presentation.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val weatherViewModel: WeatherViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        weatherViewModel.weather.observe(this, Observer { weather ->
            binding.tvCity.text = weather.cityName
            binding.tvTemperature.text = "${weather.temperature}°C"
            binding.tvFeelsLike.text = "Feels like ${weather.feelsLike}°C, ${weather.description}."
            binding.tvMinMax.text = "min: ${weather.tempMin}°C, max: ${weather.tempMax}°C"
            binding.tvPressure.text = "pressure: ${weather.pressure} hPa"
            binding.tvHumidity.text = "humidity: ${weather.humidity}%"
            binding.tvVisibility.text = "visibility: ${weather.visibility / 1000.0} km"
            binding.tvWindSpeed.text = "wind speed: ${weather.windSpeed} m/s"
            binding.tvWindGust.text = "gust: ${weather.windGust} m/s"
            binding.tvWindDirection.text = "direction: ${weather.windDirection}"
        })

        weatherViewModel.loadWeather("Saratov", "507280dae590ced921c1cfaf596b563d")
    }
}