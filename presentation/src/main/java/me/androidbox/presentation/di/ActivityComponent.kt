package me.androidbox.presentation.di

import dagger.Component
import me.androidbox.presentation.di.scopes.ActivityScope
import me.androidbox.presentation.forecast.ForecastActivity
import me.androidbox.presentation.forecast.ForecastFragment
import me.androidbox.presentation.forecast.RetryFragment

@ActivityScope
@Component(dependencies = [GoWeatherComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(forecastActivity: ForecastActivity)
    fun inject(retryFragment: RetryFragment)
    fun inject(forecastFragment: ForecastFragment)
}
