package me.androidbox.presentation.mappers

import me.androidbox.models.ForecastModel
import me.androidbox.models.WeatherForecastModel
import me.androidbox.presentation.models.Forecast
import me.androidbox.presentation.models.Weather
import me.androidbox.presentation.models.WeatherForecast

class WeatherForecastPresentationMapperImp : WeatherForecastPresentationMapper {
    override fun map(domain: WeatherForecastModel): WeatherForecast {
        return WeatherForecast(
            mapToForecast(domain.forecast),
            domain.cityName,
            domain.timeZone,
            domain.countryCode,
            domain.stateCode)
    }

    private fun mapToForecast(forecastModelList: List<ForecastModel>): List<Forecast> {
        val forecastList = mutableListOf<Forecast>()

        forecastModelList.forEach {
            forecastList.add(
                Forecast(
                    it.temp,
                    it.highTemp,
                    it.lowTemp,
                    it.feelsLikeMinTemp,
                    it.feelsLikeMaxTemp,
                    it.validDate,
                    Weather(it.weather.icon, it.weather.code, it.weather.description)
                ))
        }

        return forecastList.toList()
    }
}
