package me.androidbox.presentation.forecast.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.androidbox.interactors.current.CurrentWeatherInteractor
import me.androidbox.interactors.forecast.WeatherForecastInteractor
import me.androidbox.presentation.base.BaseViewModel
import me.androidbox.presentation.common.SchedulerProvider
import me.androidbox.presentation.mappers.CurrentWeatherPresentationMapper
import me.androidbox.presentation.mappers.WeatherForecastPresentationMapper
import me.androidbox.presentation.models.WeatherForecast

class ForecastViewModel(private val weatherForecastInteractor: WeatherForecastInteractor,
                        private val weatherForecastPresentationMapper: WeatherForecastPresentationMapper,
                        private val schedulerProvider: SchedulerProvider,
                        private val currentWeatherInteractor: CurrentWeatherInteractor,
                        private val currentWeatherPresentationMapper: CurrentWeatherPresentationMapper
)
    : BaseViewModel() {

    val data = MutableLiveData<WeatherForecast>()

}
