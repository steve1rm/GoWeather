package me.androidbox.presentation.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import me.androidbox.interactors.WeatherForecast
import me.androidbox.interactors.WeatherForecastInteractor
import me.androidbox.interactors.WeatherForecastInteractorImp
import me.androidbox.presentation.common.LocationUtils
import me.androidbox.presentation.common.LocationUtilsImp
import me.androidbox.presentation.forecast.ForecastPresenter
import me.androidbox.presentation.forecast.ForecastPresenterImp
import me.androidbox.presentation.mappers.WeatherForecastPresentationMapper
import me.androidbox.presentation.mappers.WeatherForecastPresentationMapperImp
import javax.inject.Named

@Module
class TestActivityModule {
    @Reusable
    @Provides
    fun provideWeatherForecastInteractor(weatherForecast: WeatherForecast): WeatherForecastInteractor {
        return WeatherForecastInteractorImp(weatherForecast)
    }

    @Reusable
    @Provides
    fun provideForecastPresenter(weatherForecastInteractor: WeatherForecastInteractor,
                                 weatherForecastPresentationMapper: WeatherForecastPresentationMapper): ForecastPresenter =
            ForecastPresenterImp(weatherForecastInteractor, weatherForecastPresentationMapper)

    @Reusable
    @Provides
    fun provideWeatherForecastPresentationMapper(): WeatherForecastPresentationMapper =
            WeatherForecastPresentationMapperImp()

    @Reusable
    @Provides
    fun provideLocationUtils(): LocationUtils {
        return LocationUtilsImp()
    }

/*
    @Reusable
    @Provides
    fun provideRetryListener(): RetryListener {
        return forecastActivity
    }
*/
}

