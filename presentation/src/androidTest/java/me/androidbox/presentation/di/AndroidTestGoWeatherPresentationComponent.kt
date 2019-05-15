package me.androidbox.presentation.di

import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import me.androidbox.presentation.forecast.ForecastActivityAndroidTest
import javax.inject.Singleton

@Singleton
@Component(modules = [TestNetworkModule::class, AndroidSupportInjectionModule::class])
interface AndroidTestGoWeatherPresentationComponent : GoWeatherComponent {
    fun inject(forecastActivityAndroidTest: ForecastActivityAndroidTest)
}
