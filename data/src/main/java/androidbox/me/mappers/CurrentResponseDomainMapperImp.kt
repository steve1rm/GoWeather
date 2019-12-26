package androidbox.me.mappers

import androidbox.me.entities.response.CurrentWeatherEntity
import me.androidbox.models.response.CurrentModel
import me.androidbox.models.response.WeatherModel

class CurrentResponseDomainMapperImp : CurrentResponseDomainMapper {
    override fun map(entity: CurrentWeatherEntity): CurrentModel {
       return entity.currentWeatherData.map {
            CurrentModel(it.cityName,
            it.stateCode,
            it.feelsLikeTemperature,
            it.temperature,
            WeatherModel(it.weather.icon, it.weather.code, it.weather.description))
        }.first()
    }
}
