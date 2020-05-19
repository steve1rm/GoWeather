package me.androidbox.presentation.di.forecast

import android.content.Context
import androidbox.me.local.DatabaseService
import androidx.fragment.app.Fragment
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
import me.androidbox.presentation.adapters.BaseDelegate
import me.androidbox.presentation.adapters.ForecastAdapter
import me.androidbox.presentation.adapters.ForecastDelegate
import me.androidbox.presentation.base.BaseFragment
import me.androidbox.presentation.common.SchedulerProvider
import me.androidbox.presentation.di.scopes.ActivityScope
import me.androidbox.presentation.di.scopes.FragmentScope
import me.androidbox.presentation.forecast.mvp.ForecastPresenter
import me.androidbox.presentation.forecast.mvp.ForecastPresenterImp
import me.androidbox.presentation.forecast.mvvm.ForecastViewModel
import me.androidbox.presentation.mappers.CurrentWeatherPresentationMapper
import me.androidbox.presentation.mappers.CurrentWeatherPresentationMapperImp
import me.androidbox.presentation.mappers.WeatherForecastPresentationMapper
import me.androidbox.presentation.mappers.WeatherForecastPresentationMapperImp
import me.androidbox.presentation.models.Forecast
import me.androidbox.presentation.utils.NetworkHelper
import me.androidbox.presentation.utils.ViewModelProviderFactory

@Module
class ForecastFragmentModule(private val forecastFragment: BaseFragment<*>, private val context: Context) {

    @FragmentScope
    @Provides
    fun provideFragment(): Fragment = forecastFragment

    @FragmentScope
    @Provides
    fun providesForecastAdapter(forecastDelegate: BaseDelegate<Forecast>): ForecastAdapter =
        ForecastAdapter(forecastDelegate)

    @FragmentScope
    @Provides
    fun providesForecastDelegate(): BaseDelegate<Forecast> =
        ForecastDelegate(1)

    @FragmentScope
    @Provides
    fun provideNetworkHelper() : NetworkHelper {
        return NetworkHelper()
    }

    @FragmentScope
    @Provides
    fun provideViewModel(weatherForecastInteractor: WeatherForecastInteractor,
                         weatherForecastPresentationMapper: WeatherForecastPresentationMapper,
                         schedulerProvider: SchedulerProvider,
                         currentWeatherInteractor: CurrentWeatherInteractor,
                         currentWeatherPresentationMapper: CurrentWeatherPresentationMapper,
                         databaseService: DatabaseService): ForecastViewModel {
        return ViewModelProviders.of(
            forecastFragment,
            ViewModelProviderFactory(ForecastViewModel::class) {
            ForecastViewModel(
                weatherForecastInteractor,
                weatherForecastPresentationMapper,
                schedulerProvider,
                currentWeatherInteractor,
                currentWeatherPresentationMapper,
                databaseService)
        }).get(ForecastViewModel::class.java)
    }

    @FragmentScope
    @Provides
    fun provideWeatherForecastInteractor(weatherForecast: WeatherForecast): WeatherForecastInteractor {
        return WeatherForecastInteractorImp(
            weatherForecast
        )
    }

    @FragmentScope
    @Provides
    fun provideWeatherForecastPresentationMapper(): WeatherForecastPresentationMapper =
        WeatherForecastPresentationMapperImp()

    @FragmentScope
    @Provides
    fun provideCurrentWeatherPresentationMapper(): CurrentWeatherPresentationMapper =
        CurrentWeatherPresentationMapperImp()

    @FragmentScope
    @Provides
    fun provideForecastPresenter(weatherForecastInteractor: WeatherForecastInteractor,
                                 weatherForecastPresentationMapper: WeatherForecastPresentationMapper,
                                 schedulerProvider: SchedulerProvider,
                                 currentWeatherInteractor: CurrentWeatherInteractor,
                                 currentWeatherPresentationMapper: CurrentWeatherPresentationMapper): ForecastPresenter {
        return ForecastPresenterImp(
            weatherForecastInteractor,
            weatherForecastPresentationMapper,
            schedulerProvider,
            currentWeatherInteractor,
            currentWeatherPresentationMapper)
    }

    @FragmentScope
    @Provides
    fun provideCurrentWeatherInteractor(currentWeatherRequest: CurrentWeatherRequest): CurrentWeatherInteractor {
        return CurrentWeatherInteractorImp(currentWeatherRequest)
    }
}
