package me.androidbox.presentation.di.forecast

import android.app.Activity
import android.content.Context
import androidbox.me.local.DatabaseService
import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import me.androidbox.interactors.current.CurrentWeatherInteractor
import me.androidbox.interactors.current.CurrentWeatherInteractorImp
import me.androidbox.interactors.current.CurrentWeatherRequest
import me.androidbox.interactors.forecast.WeatherForecast
import me.androidbox.interactors.forecast.WeatherForecastInteractor
import me.androidbox.interactors.forecast.WeatherForecastInteractorImp
import me.androidbox.presentation.base.BaseActivity
import me.androidbox.presentation.common.LocationUtils
import me.androidbox.presentation.common.LocationUtilsImp
import me.androidbox.presentation.common.SchedulerProvider
import me.androidbox.presentation.di.scopes.ActivityScope
import me.androidbox.presentation.forecast.mvp.ForecastPresenter
import me.androidbox.presentation.forecast.mvp.ForecastPresenterImp
import me.androidbox.presentation.forecast.mvvm.ForecastViewModel
import me.androidbox.presentation.mappers.CurrentWeatherPresentationMapper
import me.androidbox.presentation.mappers.CurrentWeatherPresentationMapperImp
import me.androidbox.presentation.mappers.WeatherForecastPresentationMapper
import me.androidbox.presentation.mappers.WeatherForecastPresentationMapperImp
import me.androidbox.presentation.router.*
import me.androidbox.presentation.utils.NetworkHelper
import me.androidbox.presentation.utils.ViewModelProviderFactory

@Module
class ForecastActivityModule(private val forecastActivity: BaseActivity<*>) {

    @ActivityScope
    @Provides
    fun provideActivity(): Activity = forecastActivity
/*
    @ActivityScope
    @Provides
    fun provideContext(activity: Activity): Context {
        return activity
    }*/

    @ActivityScope
    @Provides
    fun provideWeatherForecastInteractor(weatherForecast: WeatherForecast): WeatherForecastInteractor {
        return WeatherForecastInteractorImp(weatherForecast)
    }

    @ActivityScope
    @Provides
    fun provideCurrentWeatherInteractor(currentWeatherRequest: CurrentWeatherRequest): CurrentWeatherInteractor {
        return CurrentWeatherInteractorImp(currentWeatherRequest)
    }

    @ActivityScope
    @Provides
    fun provideCurrentWeatherPresentationMapper(): CurrentWeatherPresentationMapper =
        CurrentWeatherPresentationMapperImp()

    @ActivityScope
    @Provides
    fun provideForecastPresenter(weatherForecastInteractor: WeatherForecastInteractor,
                                 weatherForecastPresentationMapper: WeatherForecastPresentationMapper,
                                 schedulerProvider: SchedulerProvider,
                                 currentWeatherInteractor: CurrentWeatherInteractor,
                                 currentWeatherPresentationMapper: CurrentWeatherPresentationMapper
    ): ForecastPresenter =
        ForecastPresenterImp(
            weatherForecastInteractor,
            weatherForecastPresentationMapper,
            schedulerProvider,
            currentWeatherInteractor,
            currentWeatherPresentationMapper)

    @ActivityScope
    @Provides
    fun provideWeatherForecastPresentationMapper(): WeatherForecastPresentationMapper =
            WeatherForecastPresentationMapperImp()

    @ActivityScope
    @Provides
    fun provideLocationUtils(): LocationUtils {
        return LocationUtilsImp(forecastActivity)
    }

    @ActivityScope
    @Provides
    fun provideViewModel(weatherForecastInteractor: WeatherForecastInteractor,
                         weatherForecastPresentationMapper: WeatherForecastPresentationMapper,
                         schedulerProvider: SchedulerProvider,
                         currentWeatherInteractor: CurrentWeatherInteractor,
                         currentWeatherPresentationMapper: CurrentWeatherPresentationMapper,
                         databaseService: DatabaseService): ForecastViewModel {
        return ViewModelProviders.of(
            forecastActivity,
            ViewModelProviderFactory(ForecastViewModel::class) {
                ForecastViewModel(weatherForecastInteractor,
                    weatherForecastPresentationMapper,
                    schedulerProvider,
                    currentWeatherInteractor,
                    currentWeatherPresentationMapper,
                    databaseService)
            }).get(ForecastViewModel::class.java)
    }

    @ActivityScope
    @Provides
    fun provideForecastFragmentRouter(): ForecastFragmentRouter =
        ForecastFragmentRouterImp(forecastActivity.supportFragmentManager)

    @ActivityScope
    @Provides
    fun provideLoadingFragmentRouter(): LoadingFragmentRouter =
        LoadingFragmentRouterImp(forecastActivity.supportFragmentManager)

    @ActivityScope
    @Provides
    fun provideRetryFragmentRouter(): RetryFragmentRouter =
        RetryFragmentRouterImp(forecastActivity.supportFragmentManager)
}

