package androidbox.me.mappers

import androidbox.me.entities.*
import me.androidbox.models.*
import javax.inject.Inject
import kotlin.properties.Delegates

class ForecastRequestDomainMapperImp : ForecastRequestDomainMapper {
    override fun map(entity: WeatherForecastEntity): WeatherForecastModel {
        return WeatherForecastModel(
            LocationModel("", "", ""),
            CurrentModel(12),
            ForecastModel(listOf())
        )
    }

    private fun mapToLocationModel(location: LocationEntity): LocationModel {
        return LocationModel(location.name, location.region, location.country)
    }

    private fun mapToCurrentModel(current: CurrrentEntity): CurrentModel {
        return CurrentModel(current.temperatureInCelsius.toInt())
    }

    private fun mapToForecastModel(forecast: ForecastEntity): ForecastModel {
        val forecastDayList = mutableListOf<ForecastDayModel>()

   /*     forecast.forecastDay.forEach {
            forecastDayList.add(ForecastDayModel(
                it.date,
                it.dateEpoch,
                mapToForecastDay(it.day)))
        }*/

        return ForecastModel(forecastDayList.toList())
    }

    private fun mapToForecastDay(day: DayEntity): DayModel {
        return DayModel(day.averageTemperatureInCelsius)
    }
}

