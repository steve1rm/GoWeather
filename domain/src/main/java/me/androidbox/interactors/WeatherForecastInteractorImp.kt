package me.androidbox.interactors

import io.reactivex.Single
import me.androidbox.models.ForecastModel
import me.androidbox.models.ForecastRequestModel
import me.androidbox.models.WeatherForecastModel

class WeatherForecastInteractorImp(private val weatherForecast: WeatherForecast) : WeatherForecastInteractor {
    override fun requestWeatherForecast(forecastRequestModel: ForecastRequestModel): Single<WeatherForecastModel> {
        return weatherForecast.requestWeatherForecast(forecastRequestModel)
            .removeDaysFromForecast(1)
    }

    private fun <T: WeatherForecastModel> Single<T>.removeDaysFromForecast(daysToRemove: Int): Single<WeatherForecastModel> {
        return map {
            /* where not interested in the first one as we want the next 4 days */
            val forecastDayList = it.forecast.forecastDay.drop(daysToRemove)
            val forecastModel = ForecastModel(forecastDayList)
            val weatherForecastModel = WeatherForecastModel(it.location, it.current, forecastModel)

            weatherForecastModel.forecast.forecastDay.toMutableList().addAll(forecastDayList)
            weatherForecastModel
        }
    }
}
