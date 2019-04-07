package androidbox.me.network

import androidbox.me.entities.WeatherForecastEntity
import io.reactivex.Single

interface ForecastRequest {
    fun getWeatherForecast(latitude: Float, longitude: Float, days: Int): Single<WeatherForecastEntity>
}