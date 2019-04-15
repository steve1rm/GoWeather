package me.androidbox.presentation.forecast

import me.androidbox.presentation.models.WeatherForecast

interface ForecastView {
    fun onForecastSuccess(weatherForecast: WeatherForecast)
    fun onForecastFailure(error: String)
}
