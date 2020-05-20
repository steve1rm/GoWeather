package me.androidbox.presentation.forecast.mvvm

import androidbox.me.local.DatabaseService
import androidbox.me.local.tables.ForecastTable
import androidbox.me.local.tables.WeatherTable
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import me.androidbox.interactors.current.CurrentWeatherInteractor
import me.androidbox.interactors.forecast.WeatherForecastInteractor
import me.androidbox.presentation.base.BaseViewModel
import me.androidbox.presentation.common.SchedulerProvider
import me.androidbox.presentation.mappers.CurrentWeatherPresentationMapper
import me.androidbox.presentation.mappers.WeatherForecastPresentationMapper
import me.androidbox.presentation.models.Forecast
import me.androidbox.presentation.models.WeatherForecast
import timber.log.Timber

class ForecastViewModel(private val weatherForecastInteractor: WeatherForecastInteractor,
                        private val weatherForecastPresentationMapper: WeatherForecastPresentationMapper,
                        private val schedulerProvider: SchedulerProvider,
                        private val currentWeatherInteractor: CurrentWeatherInteractor,
                        private val currentWeatherPresentationMapper: CurrentWeatherPresentationMapper,
                        private val databaseService: DatabaseService)
    : BaseViewModel() {

    val data = MutableLiveData<WeatherForecast>()
    val forecastDataList = MutableLiveData<List<ForecastTable>>()
    val weatherList = MutableLiveData<List<WeatherTable>>()

    override fun onCreate() {
        createWeatherForecast()
    }

    private fun createWeatherForecast() {
        val disposable = databaseService
            .weatherDao()
            .insert(WeatherTable(icon= "icon", code = (10..1000).shuffled().first(), description = "description"))
            .flatMap { weatherId ->
                databaseService.forecastDao().insert(
                    ForecastTable(
                        temp = 45.2F,
                        highTemp = 50.5F,
                        lowTemp = 34.8F,
                        feelLikeMinTemp = 30.5F ,
                        feelLikeMaxTemp = 49.9F,
                        validDate = "today",
                        weatherId = weatherId)
                )
            }
            .flatMap {
                databaseService.forecastDao().getAllForecast()
            }
            .toObservable()
            .flatMapIterable { it }
            .flatMap {
                databaseService.weatherDao().getWeatherById(it.weatherId).toObservable()
            }
            .toList()
            .subscribeOn(schedulerProvider.backgroundScheduler())
            .observeOn(schedulerProvider.uiScheduler())
            .subscribeBy(
                onSuccess = {
                    Timber.d("Weather forecast $it")
                    weatherList.postValue(it)
                },
                onError = { Timber.e(it, it.localizedMessage) }
            )
    }

    private fun createForecastDao() {
        val disposible = databaseService
            .forecastDao()
            .insert(ForecastTable(
                temp = 45.2F,
                highTemp = 50.5F,
                lowTemp = 34.8F,
                feelLikeMinTemp = 30.5F ,
                feelLikeMaxTemp = 49.9F,
                validDate = "today",
                weatherId = 0L))
            .flatMap { numberOfInsertions ->
                println("Number of records inserted $numberOfInsertions")
                databaseService.forecastDao().getAllForecast()
            }
            .subscribeOn(schedulerProvider.backgroundScheduler())
            .observeOn(schedulerProvider.uiScheduler())
            .subscribeBy(
                onSuccess = { forecastList ->
                    println("weather list $forecastList")
                    forecastDataList.postValue(forecastList)
                },
                onError = {
                    println(it.message)
                }
            )
    }
}

