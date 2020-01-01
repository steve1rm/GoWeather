package me.androidbox.interactors.di

import com.nhaarman.mockitokotlin2.mock
import dagger.Module
import dagger.Provides
import dagger.Reusable
import me.androidbox.interactors.current.CurrentWeatherRequest
import me.androidbox.interactors.forecast.WeatherForecast

@Module
class TestInteractorDomainModule {

    @Reusable
    @Provides
    fun provideWeatherForecast(): WeatherForecast = mock()

    @Reusable
    @Provides
    fun provideCurrentWeatherRequest(): CurrentWeatherRequest = mock()
}
