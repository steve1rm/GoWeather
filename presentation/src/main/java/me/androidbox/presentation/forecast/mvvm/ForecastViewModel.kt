package me.androidbox.presentation.forecast.mvvm

import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import me.androidbox.presentation.base.BaseViewModel
import me.androidbox.presentation.models.WeatherForecast
import me.androidbox.presentation.utils.NetworkHelper

class ForecastViewModel(
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper)
    : BaseViewModel(compositeDisposable, networkHelper) {

    val data = MutableLiveData<WeatherForecast>()

    override fun onCreate() {
        // data.postValue()
    }
}
