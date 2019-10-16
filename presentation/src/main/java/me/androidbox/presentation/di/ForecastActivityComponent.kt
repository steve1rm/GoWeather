package me.androidbox.presentation.di

import dagger.Component
import me.androidbox.presentation.di.application.GoWeatherApplicationComponent
import me.androidbox.presentation.di.scopes.ActivityScope
import me.androidbox.presentation.forecast.ForecastActivity
import me.androidbox.presentation.forecast.RetryFragment

@ActivityScope
@Component(
    dependencies = [GoWeatherApplicationComponent::class],
    modules = [ForecastActivityModule::class])
interface ForecastActivityComponent {
    fun inject(forecastActivity: ForecastActivity)
    fun inject(retryFragment: RetryFragment)
}
