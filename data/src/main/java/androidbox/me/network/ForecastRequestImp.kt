package androidbox.me.network

import androidbox.me.mappers.ForecastRequestDomainMapper
import androidbox.me.mappers.ForecastRequestEntityMapper
import io.reactivex.Single
import me.androidbox.interactors.WeatherForecast
import me.androidbox.models.ForecastRequestModel
import me.androidbox.models.WeatherForecastModel

class ForecastRequestImp(private val weatherForecastService: WeatherForecastService,
                         private val apiKey: String,
                         private val forecastRequestEntityMapper: ForecastRequestEntityMapper,
                         private val forecastRequestDomainMapper: ForecastRequestDomainMapper) : WeatherForecast {

    override fun requestWeatherForecast(forecastRequestModel: ForecastRequestModel): Single<WeatherForecastModel> {
        val forecastRequestEntity = forecastRequestEntityMapper.map(forecastRequestModel)

        // val query = buildLocationQuery(forecastRequestEntity.latitude, forecastRequestEntity.longitude)
        val query = "bangkok"

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
