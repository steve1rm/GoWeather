package me.androidbox.interactors.current

import io.reactivex.Single
import me.androidbox.models.request.CurrentRequestModel
import me.androidbox.models.response.CurrentWeatherModel

interface CurrentWeatherRequest {
    fun requestCurrentWeather(currentRequestModel: CurrentRequestModel): Single<CurrentWeatherModel>
}