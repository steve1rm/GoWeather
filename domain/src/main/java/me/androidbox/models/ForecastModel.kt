package me.androidbox.models

data class ForecastModel(
    val temp: Float,
    val highTemp: Float,
    val lowTemp: Float,
    val feelsLikeMinTemp: Float,
    val feelsLikeMaxTemp: Float,
    val weather: WeatherModel)
