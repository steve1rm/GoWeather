package me.androidbox.presentation.forecast

import me.androidbox.models.WeatherForecastModel

interface ForecastView {
    fun onForecastSuccess(weatherForecastModel: WeatherForecastModel)
    fun onForecastFailure(error: String)
}
