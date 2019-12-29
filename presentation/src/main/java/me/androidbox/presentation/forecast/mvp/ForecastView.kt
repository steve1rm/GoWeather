package me.androidbox.presentation.forecast.mvp

import me.androidbox.presentation.models.CurrentWeather
import me.androidbox.presentation.models.WeatherForecast

interface ForecastView {
    fun onForecastSuccess(weatherForecast: WeatherForecast, currentWeather: CurrentWeather)
    fun onForecastFailure(error: String)
}
