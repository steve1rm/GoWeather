package androidbox.me.mappers

import androidbox.me.entities.response.CurrentEntity
import androidbox.me.entities.response.DataEntity
import me.androidbox.models.response.CurrentModel
import me.androidbox.models.response.WeatherModel

class CurrentResponseDomainMapperImp : CurrentResponseDomainMapper {
    override fun map(entity: DataEntity): CurrentModel {
        return CurrentModel(entity.currentEntity[0].cityName,
            entity.currentEntity[0].stateCode,
            entity.currentEntity[0].feelsLikeTemperature,
            entity.currentEntity[0].temperature,
            WeatherModel(entity.currentEntity[0].weather.icon, entity.currentEntity[0].weather.code, entity.currentEntity[0].weather.description))
/*

        return CurrentModel(entity.cityName,
            entity.stateCode,
            entity.feelsLikeTemperature,
            entity.temperature,
            WeatherModel(entity.weather.icon, entity.weather.code, entity.weather.description))
*/
    }
}
