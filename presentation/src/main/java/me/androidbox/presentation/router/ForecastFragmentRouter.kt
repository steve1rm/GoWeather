package me.androidbox.presentation.router

import me.androidbox.presentation.models.WeatherForecast

interface ForecastFragmentRouter {
    fun goToForecastFragment(latitude: Double, longitude: Double, statusCallback: () -> Unit)
    fun goToForecastFragment(weatherForecast: WeatherForecast, statusCallback: () -> Unit)
}
