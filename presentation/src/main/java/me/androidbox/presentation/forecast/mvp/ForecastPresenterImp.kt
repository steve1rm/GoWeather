package me.androidbox.presentation.forecast.mvp

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import me.androidbox.interactors.current.CurrentWeatherInteractor
import me.androidbox.interactors.forecast.WeatherForecastInteractor
import me.androidbox.models.request.CurrentRequestModel
import me.androidbox.models.request.ForecastRequestModel
import me.androidbox.models.response.CurrentWeatherModel
import me.androidbox.models.response.WeatherForecastModel
import me.androidbox.presentation.base.BasePresenterImp
import me.androidbox.presentation.common.SchedulerProvider
import me.androidbox.presentation.mappers.WeatherForecastPresentationMapper
import me.androidbox.wrappers.Latitude
import me.androidbox.wrappers.Longitude
import javax.inject.Inject

class ForecastPresenterImp @Inject constructor(private val weatherForecastInteractor: WeatherForecastInteractor,
                                               private val weatherForecastPresentationMapper: WeatherForecastPresentationMapper,
                                               private val schedulerProvider: SchedulerProvider,
                                               private val currentWeatherInteractor: CurrentWeatherInteractor)
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

    override fun requestWeatherForecast(latitude: Latitude, longitude: Longitude, days: Int) {
        /* TODO should have mapper here */
        compositableDisposable.add(weatherForecastInteractor.requestWeatherForecast(
            ForecastRequestModel(
                latitude.value,
                longitude.value,
                days
            )
        )
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

    override fun requestCurrentWeather(latitude: Latitude, longitude: Longitude) {
        compositableDisposable.add(currentWeatherInteractor.requestCurrentWeather(
            CurrentRequestModel(latitude, longitude))
            .subscribeOn(schedulerProvider.backgroundScheduler())
            .observeOn(schedulerProvider.uiScheduler())
            .subscribeBy(
                onSuccess = { onCurrentWeatherSuccess(it) },
                onError = {  onCurrentWeatherFailure(it) }
            ))
    }

    private fun onCurrentWeatherSuccess(currentWeatherModel: CurrentWeatherModel) {
        println(currentWeatherModel.cityName)
    }

    private fun onCurrentWeatherFailure(error: Throwable) {
        println(error.message)
    }
}
