package me.androidbox.interactors.forecast

import io.reactivex.Single
import me.androidbox.models.request.ForecastRequestModel
import me.androidbox.models.response.WeatherForecastModel

interface WeatherForecast {
    fun requestWeatherForecast(forecastRequestModel: ForecastRequestModel): Single<WeatherForecastModel>
}
