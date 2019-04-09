package androidbox.me.mappers

import androidbox.me.entities.request.ForecastRequestEntity
import me.androidbox.models.ForecastRequestModel

class ForecastRequestEntityMapperImp : ForecastRequestEntityMapper {
    override fun map(domain: ForecastRequestModel): ForecastRequestEntity {
        return ForecastRequestEntity(domain.latitude, domain.longitude, domain.days)
    }
}
