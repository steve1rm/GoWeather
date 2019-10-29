package me.androidbox.presentation.di.forecast

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import me.androidbox.interactors.WeatherForecast
import me.androidbox.interactors.WeatherForecastInteractor
import me.androidbox.interactors.WeatherForecastInteractorImp
import me.androidbox.presentation.base.BaseActivity
import me.androidbox.presentation.common.LocationUtils
import me.androidbox.presentation.common.LocationUtilsImp
import me.androidbox.presentation.common.SchedulerProvider
import me.androidbox.presentation.di.scopes.ActivityScope
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

    @ActivityScope
    @Provides
    fun provideActivity(): Activity = forecastActivity

    @ActivityScope
    @Provides
    fun provideContext(activity: Activity): Context {
        return activity
    }

    @ActivityScope
    @Provides
    fun provideWeatherForecastInteractor(weatherForecast: WeatherForecast): WeatherForecastInteractor {
        return WeatherForecastInteractorImp(weatherForecast)
    }

    @ActivityScope
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
    fun provideViewModel(compositeDisposable: CompositeDisposable, networkHelper: NetworkHelper): ForecastViewModel {
        return ViewModelProviders.of(
            forecastActivity,
            ViewModelProviderFactory(ForecastViewModel::class) {
                ForecastViewModel(compositeDisposable, networkHelper)
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

