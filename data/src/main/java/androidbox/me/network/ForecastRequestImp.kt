package androidbox.me.network

import androidbox.me.mappers.ForecastRequestDomainMapper
import androidbox.me.mappers.ForecastRequestEntityMapper
import io.reactivex.Single
import me.androidbox.interactors.WeatherForecast
import me.androidbox.models.ForecastRequestModel
import me.androidbox.models.WeatherForecastModel
import java.util.concurrent.TimeUnit

class ForecastRequestImp(private val weatherForecastService: WeatherForecastService,
                                             private val apiKey: String,
                                             private val forecastRequestEntityMapper: ForecastRequestEntityMapper,
                                             private val forecastRequestDomainMapper: ForecastRequestDomainMapper) : WeatherForecast {

    override fun requestWeatherForecast(forecastRequestModel: ForecastRequestModel): Single<WeatherForecastModel> {
        val forecastRequestEntity = forecastRequestEntityMapper.map(forecastRequestModel)
        val query = buildLocationQuery(forecastRequestEntity.latitude, forecastRequestEntity.longitude)

        return weatherForecastService.forecast(apiKey, query, forecastRequestEntity.days)
            .timeout(10, TimeUnit.SECONDS)
            .map {
                forecastRequestDomainMapper.map(it)
            }
    }

    private fun buildLocationQuery(latitude: Double, longitude: Double): String {
        return StringBuilder().let {
            it.append(latitude)
            it.append(",")
            it.append(longitude)

            String(it)
        }
    }
}
