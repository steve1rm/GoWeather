package me.androidbox.presentation.forecast

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.androidbox.interactors.WeatherForecastInteractor
import me.androidbox.models.ForecastRequestModel
import me.androidbox.models.WeatherForecastModel
import me.androidbox.presentation.common.BasePresenterImp
import me.androidbox.presentation.mappers.WeatherForecastPresentationMapper
import java.util.concurrent.TimeUnit

class ForecastPresenterImp(private val weatherForecastInteractor: WeatherForecastInteractor,
                           private val weatherForecastPresentationMapper: WeatherForecastPresentationMapper)
    :
    BasePresenterImp<ForecastView>(),
    ForecastPresenter {

    private val compositableDisposable = CompositeDisposable()

    override fun initialize(forecastView: ForecastView) {
        if(!isViewAttached()) {
            attachView(forecastView)
        }
    }

    override fun release() {
        detachView()
        compositableDisposable.clear()
    }

    override fun requestWeatherForecast(latitude: Double, longitude: Double) {
        compositableDisposable.add(weatherForecastInteractor.requestWeatherForecast(ForecastRequestModel(latitude, longitude, 5))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                ::onWeatherForecastSuccess,
                ::onWeatherForecastFailure))
    }

    private fun onWeatherForecastSuccess(weatherForecastModel: WeatherForecastModel) {
        val weatherForecast = weatherForecastPresentationMapper.map(weatherForecastModel)

        getView()?.onForecastSuccess(weatherForecast)
    }

    private fun onWeatherForecastFailure(error: Throwable) {
        getView()?.onForecastFailure(error.message ?: "Unspecified Error")
    }
}
