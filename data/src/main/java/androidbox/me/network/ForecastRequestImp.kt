package androidbox.me.network

import androidbox.me.mappers.ForecastRequestDomainMapper
import androidbox.me.mappers.ForecastRequestEntityMapper
import io.reactivex.Single
import me.androidbox.interactors.forecast.WeatherForecast
import me.androidbox.models.request.ForecastRequestModel
import me.androidbox.models.response.WeatherForecastModel
import java.util.concurrent.TimeUnit

class ForecastRequestImp(private val weatherForecastService: WeatherForecastService,
                         private val apiKey: String,
                         private val forecastRequestEntityMapper: ForecastRequestEntityMapper,
                         private val forecastRequestDomainMapper: ForecastRequestDomainMapper) :
    WeatherForecast {

    override fun requestWeatherForecast(forecastRequestModel: ForecastRequestModel): Single<WeatherForecastModel> {
        val forecastRequestEntity = forecastRequestEntityMapper.map(forecastRequestModel)

        return weatherForecastService.forecast(apiKey, forecastRequestEntity.latitude, forecastRequestEntity.longitude, forecastRequestEntity.days)
            .timeout(10, TimeUnit.SECONDS)
            .map {
                forecastRequestDomainMapper.map(it)
            }
    }
}
