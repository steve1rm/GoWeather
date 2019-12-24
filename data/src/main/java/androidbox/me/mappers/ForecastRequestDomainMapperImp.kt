package androidbox.me.mappers

import androidbox.me.entities.response.ForecastEntity
import androidbox.me.entities.response.WeatherForecastEntity
import me.androidbox.models.response.ForecastModel
import me.androidbox.models.response.WeatherForecastModel
import me.androidbox.models.response.WeatherModel

class ForecastRequestDomainMapperImp : ForecastRequestDomainMapper {

    override fun map(entity: WeatherForecastEntity): WeatherForecastModel {
        return WeatherForecastModel(
            mapToForecastModel(entity.forecast),
            entity.cityName,
            entity.timeZone,
            entity.countryCode,
            entity.stateCode
        )
    }

    private fun mapToForecastModel(forecastEntityList: List<ForecastEntity>): List<ForecastModel> {
        return forecastEntityList.map(::entityToDomain)
    }

    private fun entityToDomain(entity: ForecastEntity): ForecastModel {
        return ForecastModel(
            entity.temp,
            entity.highTemp,
            entity.lowTemp,
            entity.feelsLikeMinTemp,
            entity.feelsLikeMaxTemp,
            entity.validDate,
            WeatherModel(
                entity.weather.icon,
                entity.weather.code,
                entity.weather.description
            )
        )
    }
}

