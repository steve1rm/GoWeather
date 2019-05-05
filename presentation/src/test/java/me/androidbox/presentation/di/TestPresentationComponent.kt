package me.androidbox.presentation.di

import dagger.Component
import me.androidbox.presentation.forecast.ForecastActivityTest
import javax.inject.Singleton

@Singleton
@Component(modules = [ActivityTestModule::class])
interface TestPresentationComponent {
    fun inject(forecastActivityTest: ForecastActivityTest)
}

