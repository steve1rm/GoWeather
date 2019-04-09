package androidbox.me.network

import androidbox.me.entities.WeatherForecastEntity
import io.reactivex.Single
import me.androidbox.models.ForecastRequestModel

interface ForecastRequest {
    fun getWeatherForecast(forecastRequestModel: ForecastRequestModel): Single<WeatherForecastEntity>
}