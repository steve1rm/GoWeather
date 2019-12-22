package me.androidbox.presentation.forecast.mvp

import me.androidbox.presentation.base.BasePresenter

interface ForecastPresenter : BasePresenter<ForecastView> {
    fun initialize(forecastView: ForecastView)
    fun release()
    fun requestWeatherForecast(latitude: Double, longitude: Double, days: Int = 20)
}
