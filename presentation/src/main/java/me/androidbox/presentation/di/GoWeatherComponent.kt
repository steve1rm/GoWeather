package me.androidbox.presentation.di

import androidbox.me.di.MapperModule
import androidbox.me.di.NetworkDataServiceModule
import dagger.Component
import me.androidbox.interactors.WeatherForecast
import me.androidbox.presentation.adapters.ForecastAdapter
import me.androidbox.presentation.common.SchedulerProvider
import javax.inject.Singleton

@Singleton
@Component(modules = [
    NetworkModule::class,
    NetworkDataServiceModule::class,
    MapperModule::class,
    GoWeatherApplicationModule::class,
    ForecastModule::class])
interface GoWeatherComponent {

    fun inject(application: GoWeatherApplication)

    fun schedulerProvider(): SchedulerProvider
    fun weatherForecast(): WeatherForecast
    fun forecastAdapter(): ForecastAdapter
}
