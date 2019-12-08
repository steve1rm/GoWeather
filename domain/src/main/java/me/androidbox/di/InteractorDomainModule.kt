package me.androidbox.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import me.androidbox.interactors.forecast.WeatherForecast
import me.androidbox.interactors.forecast.WeatherForecastInteractor
import me.androidbox.interactors.forecast.WeatherForecastInteractorImp

@Module
class InteractorDomainModule {

    @Reusable
    @Provides
    fun providesWeatherForecastInteractor(weatherForecast: WeatherForecast): WeatherForecastInteractor {
        return WeatherForecastInteractorImp(
            weatherForecast
        )
    }
}