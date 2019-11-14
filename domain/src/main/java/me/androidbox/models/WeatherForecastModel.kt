package me.androidbox.models

data class WeatherForecastModel(
    val forecast: List<ForecastModel>,
    val cityName: String,
    val timeZone: String,
    val countryCode: String,
    val stateCode: String)
