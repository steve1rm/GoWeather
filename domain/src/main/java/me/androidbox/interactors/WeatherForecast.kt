package me.androidbox.interactors

import io.reactivex.Single
import me.androidbox.models.ForecastRequestModel
import me.androidbox.models.WeatherForecastModel

interface WeatherForecast {
    fun requestWeatherForecast(forecastRequestModel: ForecastRequestModel): Single<WeatherForecastModel>
}
