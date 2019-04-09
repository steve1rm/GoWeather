package androidbox.me.network

import androidbox.me.entities.WeatherForecastEntity
import androidbox.me.mappers.ForecastRequestEntityMapper
import io.reactivex.Single
import me.androidbox.models.ForecastRequestModel
import java.lang.StringBuilder

class ForecastRequestImp(private val weatherForecastService: WeatherForecastService,
                         private val apiKey: String,
                         private val forecastRequestEntityMapper: ForecastRequestEntityMapper) : ForecastRequest {

    override fun getWeatherForecast(forecastRequestModel: ForecastRequestModel): Single<WeatherForecastEntity> {
        val forecastRequestEntity = forecastRequestEntityMapper.map(forecastRequestModel)

        val query = buildLocationQuery(forecastRequestEntity.latitude, forecastRequestEntity.longitude)

        return weatherForecastService.forecast(apiKey, query, forecastRequestEntity.days)
    }

    private fun buildLocationQuery(latitude: Float, longitude: Float): String {
        return StringBuilder().let {
            it.append(latitude)
            it.append(",")
            it.append(longitude)

            String(it)
        }
    }
}
