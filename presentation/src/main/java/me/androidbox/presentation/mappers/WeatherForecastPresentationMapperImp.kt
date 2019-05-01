package me.androidbox.presentation.mappers

import me.androidbox.models.*
import me.androidbox.presentation.models.*

class WeatherForecastPresentationMapperImp : WeatherForecastPresentationMapper {
    override fun map(domain: WeatherForecastModel): WeatherForecast {
        return WeatherForecast(
            mapToLocation(domain.location),
            mapToCurrent(domain.current),
            mapToForecast(domain.forecast))
    }

    private fun mapToLocation(location: LocationModel): Location {
        return Location(location.name, location.region, location.country)
    }

    private fun mapToCurrent(current: CurrentModel): Current {
        return Current(current.temperatureInCelsius)
    }

    private fun mapToForecast(forecast: ForecastModel): Forecast {
        val forecastDayList = mutableListOf<ForecastDay>()

        forecast.forecastDay.forEach {
            forecastDayList.add(
                ForecastDay(
                    it.date,
                    it.dateEpoch,
                    mapToForecastDay(it.day)))
        }

        return Forecast(forecastDayList.toList())
    }

    private fun mapToForecastDay(day: DayModel): Day {
        return Day(day.averageTemperatureInCelsius)
    }
}