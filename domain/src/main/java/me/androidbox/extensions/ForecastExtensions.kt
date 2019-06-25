package me.androidbox.extensions

import io.reactivex.Single
import me.androidbox.models.ForecastModel
import me.androidbox.models.WeatherForecastModel

fun <T: WeatherForecastModel> Single<T>.removeDaysFromForecast(daysToRemove: Int): Single<WeatherForecastModel> {
    return map {
        /* where not interested in the first one as we want the next 4 days */
        val forecastDayList = it.forecast.forecastDay.drop(daysToRemove)
        val forecastModel = ForecastModel(forecastDayList)
        val weatherForecastModel = WeatherForecastModel(it.location, it.current, forecastModel)

        weatherForecastModel.forecast.forecastDay.toMutableList().addAll(forecastDayList)
        weatherForecastModel
    }
}