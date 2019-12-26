package androidbox.me.mappers

import androidbox.me.entities.response.CurrentEntity
import androidbox.me.entities.response.DataEntity
import me.androidbox.models.response.CurrentModel

interface CurrentResponseDomainMapper : MapperToDomain<DataEntity, CurrentModel>
