package me.androidbox.interactors.current

import io.reactivex.Single
import me.androidbox.models.request.CurrentRequestModel
import me.androidbox.models.response.CurrentModel

interface CurrentWeatherInteractor {
    fun requestCurrentWeather(currentRequestModel: CurrentRequestModel): Single<CurrentModel>
}