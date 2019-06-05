package me.androidbox.presentation.di

import dagger.Module
import dagger.Provides
import me.androidbox.presentation.adapters.BaseDelegate
import me.androidbox.presentation.adapters.ForecastAdapter
import me.androidbox.presentation.adapters.ForecastDelegate
import me.androidbox.presentation.models.ForecastDay

@Module
object TestForecastModule {

    @Provides
    @JvmStatic
    fun provideForecastAdapter(forecastDelegate: BaseDelegate<ForecastDay>): ForecastAdapter =
            ForecastAdapter(forecastDelegate)

    @Provides
    @JvmStatic
    fun provideForecastDelegate(): BaseDelegate<ForecastDay> =
            ForecastDelegate(1)
}
