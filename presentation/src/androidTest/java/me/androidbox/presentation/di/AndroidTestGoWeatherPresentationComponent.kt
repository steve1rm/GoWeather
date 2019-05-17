package me.androidbox.presentation.di

import androidbox.me.di.MapperModule
import androidbox.me.di.NetworkDataServiceModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import me.androidbox.presentation.forecast.ForecastActivityAndroidTest
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    TestNetworkModule::class,
    ActivityModule::class,
    NetworkDataServiceModule::class,
    MapperModule::class,
    GoWeatherApplicationModule::class])
interface AndroidTestGoWeatherPresentationComponent : GoWeatherComponent {
    fun inject(forecastActivityAndroidTest: ForecastActivityAndroidTest)
}
