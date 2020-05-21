package androidbox.me.mappers

import androidbox.me.entities.response.CurrentWeatherEntity
import me.androidbox.models.response.CurrentWeatherModel
import me.androidbox.models.response.WeatherModel

class CurrentResponseDomainMapperImp : CurrentResponseDomainMapper {
    override fun map(entity: CurrentWeatherEntity): CurrentWeatherModel {
       return entity.currentWeatherDataEntityList.map {
            CurrentWeatherModel(it.cityName,
            it.stateCode,
            it.feelsLikeTemperature,
            it.temperature,
            WeatherModel(it.weather.icon, it.weather.code, it.weather.description))
        }.first()
    }
}
