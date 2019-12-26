package androidbox.me.network

import androidbox.me.mappers.CurrentResponseDomainMapper
import androidbox.me.mappers.CurrentRequestEntityMapper
import io.reactivex.Single
import me.androidbox.interactors.current.CurrentWeatherRequest
import me.androidbox.models.request.CurrentRequestModel
import me.androidbox.models.response.CurrentWeatherModel
import java.util.concurrent.TimeUnit

class CurrentWeatherRequestImp(private val weatherForecastService: WeatherForecastService,
                               private val apiKey: String,
                               private val currentRequestEntityMapper: CurrentRequestEntityMapper,
                               private val currentRequestDomainMapper: CurrentResponseDomainMapper) : CurrentWeatherRequest {

    override fun requestCurrentWeather(currentRequestModel: CurrentRequestModel): Single<CurrentWeatherModel> {
        val currentRequestEntity = currentRequestEntityMapper.map(currentRequestModel)

        return weatherForecastService.current(apiKey, currentRequestEntity.latitude, currentRequestEntity.longitude)
            .timeout(10, TimeUnit.SECONDS)
            .map {
                currentRequestDomainMapper.map(it)
            }
    }
}
