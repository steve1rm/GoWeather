package me.androidbox.presentation.mappers

import me.androidbox.models.response.CurrentWeatherModel
import me.androidbox.presentation.models.CurrentWeather
import me.androidbox.presentation.models.Weather

class CurrentWeatherPresentationMapperImp : CurrentWeatherPresentationMapper {
    override fun map(domain: CurrentWeatherModel): CurrentWeather {
        return CurrentWeather(domain.cityName,
            domain.stateCode,
            domain.feelsLikeTemperature,
            domain.temperature,
            Weather(domain.weather.icon, domain.weather.code, domain.weather.description))
    }
}