package androidbox.me.mappers

import androidbox.me.entities.request.ForecastRequestEntity

class ForecastRequestEntityMapper : Mapper<ForecastRequestEntity, ForecastRequestEntity> {
    override fun map(domain: ForecastRequestEntity): ForecastRequestEntity {
        return ForecastRequestEntity(domain.latitude, domain.longitude, domain.days)
    }
}
