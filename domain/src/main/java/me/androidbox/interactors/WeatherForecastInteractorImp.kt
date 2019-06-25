package me.androidbox.interactors

import io.reactivex.Single
import me.androidbox.extensions.removeDaysFromForecast
import me.androidbox.models.ForecastRequestModel
import me.androidbox.models.WeatherForecastModel

class WeatherForecastInteractorImp(private val weatherForecast: WeatherForecast) : WeatherForecastInteractor {
    override fun requestWeatherForecast(forecastRequestModel: ForecastRequestModel): Single<WeatherForecastModel> {
        return weatherForecast.requestWeatherForecast(forecastRequestModel)
            .removeDaysFromForecast(1)
    }
}
