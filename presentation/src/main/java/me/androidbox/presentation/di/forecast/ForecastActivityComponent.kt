package me.androidbox.presentation.di.forecast

import dagger.Subcomponent
import me.androidbox.presentation.di.scopes.ActivityScope
import me.androidbox.presentation.forecast.ForecastActivity
import me.androidbox.presentation.forecast.RetryFragment

@ActivityScope
@Subcomponent(modules = [ForecastActivityModule::class])
interface ForecastActivityComponent {
    fun inject(forecastActivity: ForecastActivity)
    fun inject(retryFragment: RetryFragment)
}
