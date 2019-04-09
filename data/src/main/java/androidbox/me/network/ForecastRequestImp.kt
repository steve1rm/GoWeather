package androidbox.me.network

import androidbox.me.mappers.ForecastRequestDomainMapper
import androidbox.me.mappers.ForecastRequestEntityMapper
import io.reactivex.Single
import me.androidbox.models.ForecastRequestModel
import me.androidbox.models.WeatherForecastModel

class ForecastRequestImp(private val weatherForecastService: WeatherForecastService,
                         private val apiKey: String,
                         private val forecastRequestEntityMapper: ForecastRequestEntityMapper,
                         private val forecastRequestDomainMapper: ForecastRequestDomainMapper) : ForecastRequest {

    override fun getWeatherForecast(forecastRequestModel: ForecastRequestModel): Single<WeatherForecastModel> {
        val forecastRequestEntity = forecastRequestEntityMapper.map(forecastRequestModel)

        val query = buildLocationQuery(forecastRequestEntity.latitude, forecastRequestEntity.longitude)

        return weatherForecastService.forecast(apiKey, query, forecastRequestEntity.days)
            .map {
                forecastRequestDomainMapper.map(it)
            }
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
