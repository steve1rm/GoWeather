package me.androidbox.models

data class WeatherForecastModel(val location: LocationModel,
                                val current: CurrentModel,
                                val forecast: ForecastModel)