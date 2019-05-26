package me.androidbox.presentation.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.androidbox.presentation.forecast.ForecastFragment
import me.androidbox.presentation.forecast.ForecastActivity
import me.androidbox.presentation.forecast.ForecastActivityAndroidTest

@Module
abstract class TestSubComponentBuilder {
    @ContributesAndroidInjector(modules = [TestActivityModule::class])
    abstract fun injectIntoForecastActivity(): ForecastActivity

    @ContributesAndroidInjector
    abstract fun injectIntoForecastFragment(): ForecastFragment
}
