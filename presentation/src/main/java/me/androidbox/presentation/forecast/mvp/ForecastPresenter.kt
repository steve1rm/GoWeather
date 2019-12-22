package me.androidbox.presentation.forecast.mvp

import me.androidbox.presentation.base.BasePresenter
import me.androidbox.presentation.wrappers.Latitude
import me.androidbox.presentation.wrappers.Longitude

interface ForecastPresenter : BasePresenter<ForecastView> {
    fun initialize(forecastView: ForecastView)
    fun release()
    fun requestWeatherForecast(latitude: Latitude, longitude: Longitude, days: Int = 20)
}
