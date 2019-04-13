package me.androidbox.presentation.forecast

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.androidbox.interactors.WeatherForecastInteractor
import me.androidbox.models.ForecastRequestModel
import me.androidbox.models.WeatherForecastModel
import me.androidbox.presentation.common.BasePresenterImp

class ForecastPresenterImp(private val weatherForecastInteractor: WeatherForecastInteractor) :
    BasePresenterImp<ForecastView>(),
    ForecastPresenter{

    private val compositableDisposable = CompositeDisposable()

    override fun initialize(forecastView: ForecastView) {
        attachView(forecastView)
    }

    override fun release() {
        detachView()
        compositableDisposable.clear()
    }

    override fun requestWeatherForecast() {
        compositableDisposable.add(weatherForecastInteractor.requestWeatherForecast(ForecastRequestModel(0F, 0F, 5))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                ::onWeatherForecastSuccess,
                ::onWeatherForecastFailure
            ))
    }

    private fun onWeatherForecastSuccess(weatherForecastModel: WeatherForecastModel) {
        getView()?.onForecastSuccess(weatherForecastModel)
    }

    private fun onWeatherForecastFailure(error: Throwable) {
        getView()?.onForecastFailure(error.message ?: "Unspecified Error")
    }
}
