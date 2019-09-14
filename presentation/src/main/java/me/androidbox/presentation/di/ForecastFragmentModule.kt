package me.androidbox.presentation.di

import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import me.androidbox.presentation.adapters.BaseDelegate
import me.androidbox.presentation.adapters.ForecastAdapter
import me.androidbox.presentation.adapters.ForecastDelegate
import me.androidbox.presentation.base.BaseFragment
import me.androidbox.presentation.forecast.ForecastFragment
import me.androidbox.presentation.forecast.mvvm.ForecastViewModel
import me.androidbox.presentation.models.ForecastDay
import me.androidbox.presentation.utils.NetworkHelper
import me.androidbox.presentation.utils.ViewModelProviderFactory

@Module
class ForecastFragmentModule(private val forecastFragment: BaseFragment<*>) {

    @Provides
    fun providesForecastAdapter(forecastDelegate: BaseDelegate<ForecastDay>): ForecastAdapter =
        ForecastAdapter(forecastDelegate)

    @Provides
    fun providesForecastDelegate(): BaseDelegate<ForecastDay> =
        ForecastDelegate(1)

    @Provides
    fun provideViewModel(compositeDisposable: CompositeDisposable, networkHelper: NetworkHelper): ForecastViewModel {
        return ViewModelProviders.of(
            forecastFragment,
            ViewModelProviderFactory(ForecastViewModel::class) {
            ForecastViewModel(compositeDisposable, networkHelper)
        }).get(ForecastViewModel::class.java)
    }
}
