package me.androidbox.interactors.current

import io.reactivex.Single
import me.androidbox.models.request.CurrentRequestModel
import me.androidbox.models.response.CurrentModel

class CurrentWeatherInteractorImp(private val currentWeatherRequest: CurrentWeatherRequest) : CurrentWeatherInteractor {
    override fun requestCurrentWeather(currentRequestModel: CurrentRequestModel): Single<CurrentModel> {
        return currentWeatherRequest.requestCurrentWeather(currentRequestModel)
    }
}