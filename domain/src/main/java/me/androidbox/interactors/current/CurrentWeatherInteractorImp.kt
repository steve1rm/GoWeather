package me.androidbox.interactors.current

import io.reactivex.Single
import me.androidbox.models.request.CurrentRequestModel
import me.androidbox.models.response.CurrentWeatherModel

class CurrentWeatherInteractorImp(private val currentWeatherRequest: CurrentWeatherRequest) : CurrentWeatherInteractor {
    override fun requestCurrentWeather(currentRequestModel: CurrentRequestModel): Single<CurrentWeatherModel> {
        return currentWeatherRequest.requestCurrentWeather(currentRequestModel)
    }
}