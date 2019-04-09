package androidbox.me.network

import io.reactivex.Single
import me.androidbox.models.ForecastRequestModel
import me.androidbox.models.WeatherForecastModel

interface ForecastRequest {
    fun getWeatherForecast(forecastRequestModel: ForecastRequestModel): Single<WeatherForecastModel>
}