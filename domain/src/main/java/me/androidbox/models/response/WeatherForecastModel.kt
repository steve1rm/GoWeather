package me.androidbox.models.response

import me.androidbox.models.response.ForecastModel

data class WeatherForecastModel(
    val forecast: List<ForecastModel>,
    val cityName: String,
    val timeZone: String,
    val countryCode: String,
    val stateCode: String)
