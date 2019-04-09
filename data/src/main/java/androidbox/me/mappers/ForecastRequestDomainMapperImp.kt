package androidbox.me.mappers

import androidbox.me.entities.*
import me.androidbox.models.*

class ForecastRequestDomainMapperImp : ForecastRequestDomainMapper {
    override fun map(entity: WeatherForecastEntity): WeatherForecastModel {
        return WeatherForecastModel(
            mapToLocationModel(entity.location),
            mapToCurrentModel(entity.current),
            mapToForecastModel(entity.forecast))
    }

    private fun mapToLocationModel(location: LocationEntity): LocationModel {
        return LocationModel(location.name, location.region, location.country)
    }

    private fun mapToCurrentModel(current: CurrrentEntity): CurrentModel {
        return CurrentModel(current.temperatureInCelsius)
    }

    private fun mapToForecastModel(forecast: ForecastEntity): ForecastModel {
        return ForecastModel(mapToForecastDay(forecast.forecastDay))
    }

    private fun mapToForecastDay(forecastDay: ForecastDayEntity): ForecastDayModel {
        val dayList = mutableListOf<DayModel>()

        forecastDay.day.forEach {
            dayList.add(DayModel(it.averageTemperatureInCelsius))
        }

        return ForecastDayModel(dayList.toList())
    }
}