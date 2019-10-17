package me.androidbox.presentation.di.forecast

import dagger.Subcomponent
import me.androidbox.presentation.di.scopes.FragmentScope
import me.androidbox.presentation.forecast.ForecastFragment
import me.androidbox.presentation.forecast.RetryFragment

@FragmentScope
@Subcomponent(modules = [ForecastFragmentModule::class])
interface ForecastFragmentComponent {
    fun inject(forecastFragment: ForecastFragment)
    fun inject(retryFragment: RetryFragment)
}
