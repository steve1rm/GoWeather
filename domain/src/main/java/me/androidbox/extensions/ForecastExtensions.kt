package me.androidbox.extensions

import io.reactivex.Single
import me.androidbox.models.ForecastModel
import me.androidbox.models.WeatherForecastModel

fun <T: WeatherForecastModel> Single<T>.removeDaysFromForecast(daysToRemove: Int): Single<WeatherForecastModel> {
    return map {
        /* where not interested in the first one as we want the next 4 days */
        val forecastList = it.forecast.drop(daysToRemove)
        val weatherForecastModel = WeatherForecastModel(
            forecastList,
            it.cityName,
            it.timeZone,
            it.countryCode,
            it.stateCode)

        weatherForecastModel
    }
}
