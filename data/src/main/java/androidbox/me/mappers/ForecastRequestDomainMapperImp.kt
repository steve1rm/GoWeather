package androidbox.me.mappers

import androidbox.me.entities.ForecastEntity
import androidbox.me.entities.WeatherForecastEntity
import me.androidbox.models.ForecastModel
import me.androidbox.models.WeatherForecastModel
import me.androidbox.models.WeatherModel

class ForecastRequestDomainMapperImp : ForecastRequestDomainMapper {

    override fun map(entity: WeatherForecastEntity): WeatherForecastModel {
        return WeatherForecastModel(
            mapToForecastModel(entity.forecast),
            entity.cityName,
            entity.timeZone,
            entity.countryCode,
            entity.stateCode)
    }

    private fun mapToForecastModel(forecastEntityList: List<ForecastEntity>): List<ForecastModel> {
        val forecastModelList: MutableList<ForecastModel> = mutableListOf()

        forecastEntityList.forEach {
            forecastModelList.add(
                ForecastModel(
                    it.temp,
                    it.highTemp,
                    it.lowTemp,
                    it.feelsLikeMinTemp,
                    it.feelsLikeMaxTemp,
                    WeatherModel(it.weather.icon, it.weather.code, it.weather.description)))
        }

        return forecastModelList.toList()
    }
}

