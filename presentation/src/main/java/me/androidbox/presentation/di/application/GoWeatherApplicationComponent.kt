package me.androidbox.presentation.di.application

import androidbox.me.di.MapperModule
import androidbox.me.di.NetworkModule
import androidbox.me.di.NetworkServiceModule
import dagger.Component
import me.androidbox.presentation.di.forecast.ForecastActivityComponent
import me.androidbox.presentation.di.forecast.ForecastActivityModule
import me.androidbox.presentation.di.forecast.ForecastFragmentComponent
import me.androidbox.presentation.di.forecast.ForecastFragmentModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    NetworkModule::class,
    NetworkServiceModule::class,
    MapperModule::class,
    GoWeatherApplicationModule::class])
interface GoWeatherApplicationComponent {
    fun newForecastActivityComponent(forecastActivityModule: ForecastActivityModule): ForecastActivityComponent
    fun newForecastFragmentComponent(forecastFragmentModule: ForecastFragmentModule): ForecastFragmentComponent
}
