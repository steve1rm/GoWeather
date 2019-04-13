package me.androidbox.presentation.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import me.androidbox.interactors.WeatherForecast
import me.androidbox.interactors.WeatherForecastInteractor
import me.androidbox.interactors.WeatherForecastInteractorImp
import me.androidbox.presentation.forecast.ForecastPresenter
import me.androidbox.presentation.forecast.ForecastPresenterImp
import me.androidbox.presentation.forecast.ForecastView

@Module
class ActivityModule {

    @Reusable
    @Provides
    fun provideWeatherForecastInteractor(weatherForecast: WeatherForecast): WeatherForecastInteractor {
        return WeatherForecastInteractorImp(weatherForecast)
    }

    @Reusable
    @Provides
    fun provideForecastPresenter(weatherForecastInteractor: WeatherForecastInteractor): ForecastPresenter =
            ForecastPresenterImp(weatherForecastInteractor)
}

