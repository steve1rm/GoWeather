package me.androidbox.presentation.di.forecast

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import me.androidbox.interactors.forecast.WeatherForecast
import me.androidbox.interactors.forecast.WeatherForecastInteractor
import me.androidbox.interactors.forecast.WeatherForecastInteractorImp
import me.androidbox.presentation.adapters.BaseDelegate
import me.androidbox.presentation.adapters.ForecastAdapter
import me.androidbox.presentation.adapters.ForecastDelegate
import me.androidbox.presentation.base.BaseFragment
import me.androidbox.presentation.common.SchedulerProvider
import me.androidbox.presentation.di.scopes.FragmentScope
import me.androidbox.presentation.forecast.mvp.ForecastPresenter
import me.androidbox.presentation.forecast.mvp.ForecastPresenterImp
import me.androidbox.presentation.forecast.mvvm.ForecastViewModel
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

    @Provides
    fun providesForecastAdapter(forecastDelegate: BaseDelegate<Forecast>): ForecastAdapter =
        ForecastAdapter(forecastDelegate)

    @FragmentScope
    @Provides
    fun providesForecastDelegate(): BaseDelegate<Forecast> =
        ForecastDelegate(1)

    @FragmentScope
    @Provides
    fun provideNetworkHelper(context: Context) : NetworkHelper {
        return NetworkHelper(context)
    }

    @FragmentScope
    @Provides
    fun provideViewModel(compositeDisposable: CompositeDisposable, networkHelper: NetworkHelper): ForecastViewModel {
        return ViewModelProviders.of(
            forecastFragment,
            ViewModelProviderFactory(ForecastViewModel::class) {
            ForecastViewModel(compositeDisposable, networkHelper)
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
    fun provideForecastPresenter(weatherForecastInteractor: WeatherForecastInteractor,
                                 weatherForecastPresentationMapper: WeatherForecastPresentationMapper,
                                 schedulerProvider: SchedulerProvider): ForecastPresenter {
        return ForecastPresenterImp(
            weatherForecastInteractor, weatherForecastPresentationMapper, schedulerProvider)
    }
}
