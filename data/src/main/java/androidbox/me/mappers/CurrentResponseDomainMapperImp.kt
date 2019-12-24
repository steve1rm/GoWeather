package androidbox.me.mappers

import androidbox.me.entities.response.CurrentEntity
import me.androidbox.models.response.CurrentModel
import me.androidbox.models.response.WeatherModel

class CurrentResponseDomainMapperImp : CurrentResponseDomainMapper {
    override fun map(entity: CurrentEntity): CurrentModel {
        return CurrentModel(entity.cityName,
            entity.stateCode,
            entity.feelsLikeTemperature,
            entity.temperature,
            WeatherModel(entity.weather.icon, entity.weather.code, entity.weather.description))
    }
}
