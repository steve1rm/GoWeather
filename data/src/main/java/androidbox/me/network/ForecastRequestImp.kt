package androidbox.me.network

import androidbox.me.entities.WeatherForecastEntity
import io.reactivex.Single
import java.lang.StringBuilder

class ForecastRequestImp(private val weatherForecastService: WeatherForecastService,
                         private val apiKey: String) : ForecastRequest {

    override fun getWeatherForecast(latitude: Float, longitude: Float, days: Int): Single<WeatherForecastEntity> {
        val queryBuilder = StringBuilder()
        queryBuilder.append(latitude.toString())
        queryBuilder.append(",")
        queryBuilder.append(longitude.toString())

        return weatherForecastService.forecast(apiKey, queryBuilder.toString(), days)
    }
}
