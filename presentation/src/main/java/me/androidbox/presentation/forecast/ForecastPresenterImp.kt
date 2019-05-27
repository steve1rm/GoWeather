package me.androidbox.presentation.forecast

import io.reactivex.disposables.CompositeDisposable
import me.androidbox.interactors.WeatherForecastInteractor
import me.androidbox.models.ForecastRequestModel
import me.androidbox.models.WeatherForecastModel
import me.androidbox.presentation.common.BasePresenterImp
import me.androidbox.presentation.common.SchedulerProvider
import me.androidbox.presentation.mappers.WeatherForecastPresentationMapper

class ForecastPresenterImp(private val weatherForecastInteractor: WeatherForecastInteractor,
                           private val weatherForecastPresentationMapper: WeatherForecastPresentationMapper,
                           private val schedulerProvider: SchedulerProvider)
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

    override fun requestWeatherForecast(latitude: Double, longitude: Double, days: Int) {
        /* TODO should have mapper here */
        compositableDisposable.add(weatherForecastInteractor.requestWeatherForecast(ForecastRequestModel(latitude, longitude, days))
            .subscribeOn(schedulerProvider.backgroundScheduler())
            .observeOn(schedulerProvider.uiScheduler())
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
