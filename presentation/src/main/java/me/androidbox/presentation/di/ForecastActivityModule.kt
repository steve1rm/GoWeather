package me.androidbox.presentation.di

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.disposables.CompositeDisposable
import me.androidbox.interactors.WeatherForecast
import me.androidbox.interactors.WeatherForecastInteractor
import me.androidbox.interactors.WeatherForecastInteractorImp
import me.androidbox.presentation.base.BaseActivity
import me.androidbox.presentation.common.LocationUtils
import me.androidbox.presentation.common.LocationUtilsImp
import me.androidbox.presentation.common.LocationUtilsImp.LocationStatus
import me.androidbox.presentation.common.SchedulerProvider
import me.androidbox.presentation.forecast.ForecastActivity
import me.androidbox.presentation.forecast.RetryListener
import me.androidbox.presentation.forecast.mvp.ForecastPresenter
import me.androidbox.presentation.forecast.mvp.ForecastPresenterImp
import me.androidbox.presentation.forecast.mvvm.ForecastViewModel
import me.androidbox.presentation.mappers.WeatherForecastPresentationMapper
import me.androidbox.presentation.mappers.WeatherForecastPresentationMapperImp
import me.androidbox.presentation.router.*
import me.androidbox.presentation.utils.NetworkHelper
import me.androidbox.presentation.utils.ViewModelProviderFactory

@Module
class ForecastActivityModule(private val forecastActivity: BaseActivity<*>) {

    @Reusable
    @Provides
    fun provideContext(): Context = forecastActivity

    @Reusable
    @Provides
    fun provideWeatherForecastInteractor(weatherForecast: WeatherForecast): WeatherForecastInteractor {
        return WeatherForecastInteractorImp(weatherForecast)
    }

    @Reusable
    @Provides
    fun provideForecastPresenter(weatherForecastInteractor: WeatherForecastInteractor,
                                 weatherForecastPresentationMapper: WeatherForecastPresentationMapper,
                                 schedulerProvider: SchedulerProvider
    ): ForecastPresenter =
        ForecastPresenterImp(
            weatherForecastInteractor,
            weatherForecastPresentationMapper,
            schedulerProvider
        )

    @Reusable
    @Provides
    fun provideWeatherForecastPresentationMapper(): WeatherForecastPresentationMapper =
            WeatherForecastPresentationMapperImp()

    @Reusable
    @Provides
    fun provideLocationUtils(): LocationUtils {
        return LocationUtilsImp(forecastActivity)
    }

    @Reusable
    @Provides
    fun provideViewModel(compositeDisposable: CompositeDisposable, networkHelper: NetworkHelper): ForecastViewModel {
        return ViewModelProviders.of(
            forecastActivity,
            ViewModelProviderFactory(ForecastViewModel::class) {
                ForecastViewModel(compositeDisposable, networkHelper)
        }).get(ForecastViewModel::class.java)
    }

    @Reusable
    @Provides
    fun provideForecastFragmentRouter(): ForecastFragmentRouter =
        ForecastFragmentRouterImp(forecastActivity.supportFragmentManager)

    @Reusable
    @Provides
    fun provideLoadingFragmentRouter(): LoadingFragmentRouter =
        LoadingFragmentRouterImp(forecastActivity.supportFragmentManager)

    @Reusable
    @Provides
    fun provideRetryFragmentRouter(): RetryFragmentRouter =
        RetryFragmentRouterImp(forecastActivity.supportFragmentManager)
}

