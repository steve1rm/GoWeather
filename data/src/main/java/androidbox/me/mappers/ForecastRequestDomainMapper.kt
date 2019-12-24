package androidbox.me.mappers

import androidbox.me.entities.response.WeatherForecastEntity
import me.androidbox.models.response.WeatherForecastModel

interface ForecastRequestDomainMapper : MapperToDomain<WeatherForecastEntity, WeatherForecastModel>