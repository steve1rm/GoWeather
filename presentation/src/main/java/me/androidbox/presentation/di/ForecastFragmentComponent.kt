package me.androidbox.presentation.di

import dagger.Component
import me.androidbox.presentation.di.scopes.FragmentScope
import me.androidbox.presentation.forecast.ForecastFragment

@FragmentScope
@Component(
    dependencies = [GoWeatherComponent::class],
    modules = [ForecastFragmentModule::class])
interface ForecastFragmentComponent {
    fun inject(forecastFragment: ForecastFragment)
}
