package me.androidbox.presentation.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import me.androidbox.presentation.adapters.BaseDelegate
import me.androidbox.presentation.adapters.ForecastAdapter
import me.androidbox.presentation.adapters.ForecastDelegate
import me.androidbox.presentation.models.Forecast
import me.androidbox.presentation.models.ForecastDay

@Module
object TestForecastModule {

  /*  @Reusable
    @Provides
    @JvmStatic
    fun provideForecastAdapter(forecastDelegate: BaseDelegate<Forecast>): ForecastAdapter =
            ForecastAdapter(forecastDelegate)*/

 /*   @Reusable
    @Provides
    @JvmStatic
    fun provideForecastDelegate(): BaseDelegate<Forecast> =
            ForecastDelegate(1)*/
}
