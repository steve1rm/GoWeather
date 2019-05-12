package me.androidbox.presentation.di

import dagger.Component
import me.androidbox.presentation.forecast.ForecastActivityAndroidTest
import javax.inject.Singleton

@Singleton
@Component(modules = [TestNetworkModule::class])
interface AndroidTestGoWeatherPresentationComponent : GoWeatherComponent {
    fun inject(forecastActivityAndroidTest: ForecastActivityAndroidTest)
}
