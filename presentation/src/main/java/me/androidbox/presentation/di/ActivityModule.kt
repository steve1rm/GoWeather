package me.androidbox.presentation.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import me.androidbox.interactors.WeatherForecast
import me.androidbox.interactors.WeatherForecastInteractor
import me.androidbox.interactors.WeatherForecastInteractorImp
import me.androidbox.models.WeatherForecastModel
import me.androidbox.presentation.forecast.ForecastActivity
import me.androidbox.presentation.forecast.ForecastPresenter
import me.androidbox.presentation.forecast.ForecastPresenterImp
import me.androidbox.presentation.mappers.WeatherForecastPresentationMapper
import me.androidbox.presentation.mappers.WeatherForecastPresentationMapperImp

@Module
class ActivityModule {
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
}

