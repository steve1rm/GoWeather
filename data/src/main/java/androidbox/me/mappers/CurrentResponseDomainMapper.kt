package androidbox.me.mappers

import androidbox.me.entities.response.CurrentWeatherEntity
import me.androidbox.models.response.CurrentModel

interface CurrentResponseDomainMapper : MapperToDomain<CurrentWeatherEntity, CurrentModel>
