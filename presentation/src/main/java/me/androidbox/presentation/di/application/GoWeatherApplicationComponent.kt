package me.androidbox.presentation.di.application

import androidbox.me.di.MapperModule
import androidbox.me.di.NetworkDataServiceModule
import dagger.Component
import dagger.Reusable
import io.reactivex.disposables.CompositeDisposable
import me.androidbox.interactors.WeatherForecast
import me.androidbox.presentation.common.SchedulerProvider
import me.androidbox.presentation.di.GoWeatherApplication
import me.androidbox.presentation.di.NetworkModule
import me.androidbox.presentation.utils.NetworkHelper
import javax.inject.Singleton

@Singleton
@Component(modules = [
    NetworkModule::class,
    NetworkDataServiceModule::class,
    MapperModule::class,
    GoWeatherApplicationModule::class])
interface GoWeatherApplicationComponent {

    fun inject(application: GoWeatherApplication)

    fun schedulerProvider(): SchedulerProvider
    fun weatherForecast(): WeatherForecast
    fun compositeDisposable(): CompositeDisposable
    fun networkHelper(): NetworkHelper
}
