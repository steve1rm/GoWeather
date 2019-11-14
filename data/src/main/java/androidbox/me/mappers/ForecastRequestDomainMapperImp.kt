package androidbox.me.mappers

import androidbox.me.entities.*
import androidbox.me.entities.ForecastEntity
import me.androidbox.models.*

class ForecastRequestDomainMapperImp : ForecastRequestDomainMapper {
    override fun map(model: WeatherForecastModel): WeatherForecastModel {
        return WeatherForecastModel(
            LocationModel("", "", ""),
            CurrentModel(12),
            ForecastEntity(listOf())
        )
    }

    private fun mapToLocationModel(location: LocationEntity): LocationModel {
        return LocationModel(location.name, location.region, location.country)
    }

    private fun mapToCurrentModel(current: CurrrentEntity): CurrentModel {
        return CurrentModel(current.temperatureInCelsius.toInt())
    }

    private fun mapToForecastModel(forecast: ForecastEntity): ForecastEntity {
        val forecastDayList = mutableListOf<ForecastDayModel>()

   /*     forecast.forecastDay.forEach {
            forecastDayList.add(ForecastDayModel(
                it.date,
                it.dateEpoch,
                mapToForecastDay(it.day)))
        }*/

        return ForecastEntity(forecastDayList.toList())
    }

    private fun mapToForecastDay(day: DayEntity): DayModel {
        return DayModel(day.averageTemperatureInCelsius)
    }
}

