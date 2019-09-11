package me.androidbox.presentation.forecast.mvp

import me.androidbox.presentation.common.BasePresenter

interface ForecastPresenter : BasePresenter<ForecastView> {
    fun initialize(forecastView: ForecastView)
    fun release()
    fun requestWeatherForecast(latitude: Double, longitude: Double, days: Int)
}
