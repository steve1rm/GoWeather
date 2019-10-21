package me.androidbox.presentation.di.forecast

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.disposables.CompositeDisposable
import me.androidbox.interactors.WeatherForecast
import me.androidbox.interactors.WeatherForecastInteractor
import me.androidbox.interactors.WeatherForecastInteractorImp
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
import me.androidbox.presentation.models.ForecastDay
import me.androidbox.presentation.utils.NetworkHelper
import me.androidbox.presentation.utils.ViewModelProviderFactory

@Module
class ForecastFragmentModule(private val forecastFragment: BaseFragment<*>, private val context: Context) {

    @FragmentScope
    @Provides
    fun provideFragment(): Fragment = forecastFragment

    @FragmentScope
    @Provides
    fun provideContext(): Context = context

    @Provides
    fun providesForecastAdapter(forecastDelegate: BaseDelegate<ForecastDay>): ForecastAdapter =
        ForecastAdapter(forecastDelegate)

    @Provides
    fun providesForecastDelegate(): BaseDelegate<ForecastDay> =
        ForecastDelegate(1)

    @Reusable
    @Provides
    fun provideNetworkHelper(context: Context) : NetworkHelper {
        return NetworkHelper(context)
    }

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
        return WeatherForecastInteractorImp(weatherForecast)
    }

    @FragmentScope
    @Provides
    fun provideWeatherForecastPresentationMapper(): WeatherForecastPresentationMapper =
        WeatherForecastPresentationMapperImp()

    @Provides
    fun provideForecastPresenter(weatherForecastInteractor: WeatherForecastInteractor,
                                 weatherForecastPresentationMapper: WeatherForecastPresentationMapper,
                                 schedulerProvider: SchedulerProvider): ForecastPresenter {
        return ForecastPresenterImp(
            weatherForecastInteractor, weatherForecastPresentationMapper, schedulerProvider)
    }
}
