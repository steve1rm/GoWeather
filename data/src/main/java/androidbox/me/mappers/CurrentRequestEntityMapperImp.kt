package androidbox.me.mappers

import androidbox.me.entities.request.CurrentWeatherRequestEntity
import me.androidbox.models.request.CurrentRequestModel

class CurrentRequestEntityMapperImp : CurrentRequestEntityMapper {
    override fun map(domain: CurrentRequestModel): CurrentWeatherRequestEntity {
        return CurrentWeatherRequestEntity(domain.latitude, domain.longitude)
    }
}
