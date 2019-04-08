package androidbox.me.network

import androidbox.me.entities.WeatherForecastEntity
import io.reactivex.Single
import java.lang.StringBuilder

class ForecastRequestImp(private val weatherForecastService: WeatherForecastService,
                         private val apiKey: String) : ForecastRequest {

    override fun getWeatherForecast(latitude: Float, longitude: Float, days: Int): Single<WeatherForecastEntity> {
        val query = buildLocationQuery(latitude, longitude)

        return weatherForecastService.forecast(apiKey, query, days)
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
