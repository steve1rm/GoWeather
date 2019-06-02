package me.androidbox.presentation.di

import dagger.Module
import dagger.Provides
import me.androidbox.presentation.adapters.BaseDelegate
import me.androidbox.presentation.adapters.ForecastAdapter
import me.androidbox.presentation.adapters.ForecastDelegate
import me.androidbox.presentation.models.ForecastDay

@Module
object ForecastModule {

    @Provides
    @JvmStatic
    fun providesForecastAdapter(forecastDelegate: BaseDelegate<ForecastDay>): ForecastAdapter =
        ForecastAdapter(forecastDelegate)

    @Provides
    @JvmStatic
    fun providesForecastDelegate(): BaseDelegate<ForecastDay> =
        ForecastDelegate(1)
}
