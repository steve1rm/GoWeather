package me.androidbox.presentation.di

import com.nhaarman.mockitokotlin2.mock
import dagger.Module
import dagger.Provides
import dagger.Reusable
import me.androidbox.interactors.current.CurrentWeatherInteractor
import me.androidbox.interactors.forecast.WeatherForecast
import me.androidbox.interactors.forecast.WeatherForecastInteractor
import me.androidbox.interactors.forecast.WeatherForecastInteractorImp
import me.androidbox.presentation.common.AndroidTestSchedulerProviderImp
import me.androidbox.presentation.common.LocationUtils
import me.androidbox.presentation.common.SchedulerProvider
import me.androidbox.presentation.forecast.mvp.ForecastPresenter
import me.androidbox.presentation.forecast.mvp.ForecastPresenterImp
import me.androidbox.presentation.mappers.CurrentWeatherPresentationMapper
import me.androidbox.presentation.mappers.WeatherForecastPresentationMapper
import me.androidbox.presentation.mappers.WeatherForecastPresentationMapperImp
import javax.inject.Singleton

@Module
class TestActivityModule {
   /* @Reusable
    @Provides
    fun provideWeatherForecastInteractor(weatherForecast: WeatherForecast): WeatherForecastInteractor {
        return WeatherForecastInteractorImp(
            weatherForecast
        )
    }*/

  /*  @Reusable
    @Provides
    fun provideForecastPresenter(weatherForecastInteractor: WeatherForecastInteractor,
                                 weatherForecastPresentationMapper: WeatherForecastPresentationMapper,
                                 schedulerProvider: SchedulerProvider,
                                 currentWeatherInteractor: CurrentWeatherInteractor,
                                 currentWeatherPresentationMapper: CurrentWeatherPresentationMapper): ForecastPresenter =
        ForecastPresenterImp(
            weatherForecastInteractor,
            weatherForecastPresentationMapper,
            schedulerProvider,
            currentWeatherInteractor,
            currentWeatherPresentationMapper)*/

  /*  @Reusable
    @Provides
    fun provideWeatherForecastPresentationMapper(): WeatherForecastPresentationMapper =
            WeatherForecastPresentationMapperImp()
*/
 /*   @Reusable
    @Provides
    fun provideLocationUtils(): LocationUtils = mock()*/

    @Reusable
    @Provides
    fun provideSchedulerProvider(): SchedulerProvider {
        return AndroidTestSchedulerProviderImp()
    }
}

