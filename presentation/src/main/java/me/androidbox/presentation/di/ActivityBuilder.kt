package me.androidbox.presentation.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.androidbox.presentation.forecast.ForecastFragment
import me.androidbox.presentation.forecast.HomeActivity

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [ActivityModule::class])
    abstract fun injectIntoHomeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [ActivityModule::class])
    abstract fun injectIntoForecastFragment(): ForecastFragment
}
