package me.androidbox.presentation.forecast.mvp

import me.androidbox.presentation.base.BasePresenter
import me.androidbox.wrappers.Latitude
import me.androidbox.wrappers.Longitude

interface ForecastPresenter : BasePresenter<ForecastView> {
    fun initialize(forecastView: ForecastView)
    fun release()
    fun requestForecastAndCurrentWeather(latitude: Latitude, longitude: Longitude, days: Int)
}
