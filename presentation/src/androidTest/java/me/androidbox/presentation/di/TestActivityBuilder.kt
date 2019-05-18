package me.androidbox.presentation.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.androidbox.presentation.forecast.ForecastFragment
import me.androidbox.presentation.forecast.ForecastActivity

@Module
abstract class TestActivityBuilder {
    @ContributesAndroidInjector
    abstract fun injectIntoHomeActivity(): ForecastActivity

    @ContributesAndroidInjector()
    abstract fun injectIntoForecastFragment(): ForecastFragment
}
