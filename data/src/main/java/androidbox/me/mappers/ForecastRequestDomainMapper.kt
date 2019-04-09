package androidbox.me.mappers

import androidbox.me.entities.WeatherForecastEntity
import me.androidbox.models.WeatherForecastModel

interface ForecastRequestDomainMapper : MapperToDomain<WeatherForecastEntity, WeatherForecastModel>