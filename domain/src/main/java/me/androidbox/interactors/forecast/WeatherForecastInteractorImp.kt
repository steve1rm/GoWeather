package me.androidbox.interactors.forecast

import io.reactivex.Single
import me.androidbox.extensions.removeDaysFromForecast
import me.androidbox.interactors.dropdays.DropInitialDays
import me.androidbox.models.request.ForecastRequestModel
import me.androidbox.models.response.WeatherForecastModel

class WeatherForecastInteractorImp(private val weatherForecast: WeatherForecast) :
    WeatherForecastInteractor {

    override fun requestWeatherForecast(forecastRequestModel: ForecastRequestModel): Single<WeatherForecastModel> {
        return weatherForecast.requestWeatherForecast(forecastRequestModel)
            .removeDaysFromForecast(DropInitialDays.numberOfDays())
    }
}
