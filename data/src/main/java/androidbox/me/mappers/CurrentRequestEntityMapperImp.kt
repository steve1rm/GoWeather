package androidbox.me.mappers

import androidbox.me.entities.request.CurrentRequestEntity
import me.androidbox.models.request.CurrentRequestModel

class CurrentRequestEntityMapperImp : CurrentRequestEntityMapper {
    override fun map(domain: CurrentRequestModel): CurrentRequestEntity {
        return CurrentRequestEntity(domain.latitude, domain.longitude)
    }
}
