package me.androidbox.presentation.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.androidbox.presentation.forecast.ForecastFragment
import me.androidbox.presentation.forecast.ForecastActivity

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [ActivityModule::class])
    abstract fun injectIntoHomeActivity(): ForecastActivity

    @ContributesAndroidInjector(modules = [ActivityModule::class])
    abstract fun injectIntoForecastFragment(): ForecastFragment
}
