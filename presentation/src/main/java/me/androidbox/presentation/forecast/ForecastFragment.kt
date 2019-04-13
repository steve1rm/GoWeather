package me.androidbox.presentation.forecast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjection
import me.androidbox.interactors.WeatherForecastInteractor
import me.androidbox.models.WeatherForecastModel

import me.androidbox.presentation.R
import javax.inject.Inject

class ForecastFragment : Fragment(), ForecastView {

    @Inject
    lateinit var forecastPresenter: ForecastPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AndroidSupportInjection.inject(this)
        val view = inflater.inflate(R.layout.fragment_forecast, container, false)

        forecastPresenter.initialize(this)
        forecastPresenter.requestWeatherForecast()

        return view
    }

    override fun onDestroyView() {
        forecastPresenter.release()
        super.onDestroyView()
    }

    override fun onForecastSuccess(weatherForecastModel: WeatherForecastModel) {
        println("onForecastSuccess ${weatherForecastModel.forecast.forecastDay[0].day.averageTemperatureInCelsius}")
    }

    override fun onForecastFailure(error: String) {
        println("onForecastFailure $error")
    }
}
