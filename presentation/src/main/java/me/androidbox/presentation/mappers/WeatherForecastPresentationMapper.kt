package me.androidbox.presentation.mappers

import me.androidbox.models.response.WeatherForecastModel
import me.androidbox.presentation.models.WeatherForecast

interface WeatherForecastPresentationMapper : MapperToPresentation<WeatherForecastModel, WeatherForecast>
