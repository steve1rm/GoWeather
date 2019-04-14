package me.androidbox.presentation.forecast

import android.animation.AnimatorInflater
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.inprogress.*
import kotlinx.android.synthetic.main.weather_forecast.*
import kotlinx.android.synthetic.main.weather_forecast_header.*
import me.androidbox.models.WeatherForecastModel
import me.androidbox.presentation.R
import me.androidbox.presentation.adapters.ForecastAdapter
import me.androidbox.presentation.adapters.ForecastDelegate
import javax.inject.Inject

class ForecastFragment : Fragment(), ForecastView {

    @Inject
    lateinit var forecastPresenter: ForecastPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AndroidSupportInjection.inject(this)
        return inflater.inflate(R.layout.weather_forecast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        forecastPresenter.initialize(this)
        forecastPresenter.requestWeatherForecast()
     //   startLoading()
    }

    private fun startLoading() {
        val rotation = AnimatorInflater.loadAnimator(activity, R.animator.loading_progress)
        rotation.setTarget(ivProgress)
        rotation.start()
    }

    override fun onDestroyView() {
        forecastPresenter.release()
        super.onDestroyView()
    }

    override fun onForecastSuccess(weatherForecastModel: WeatherForecastModel) {
        println("onForecastSuccess ${weatherForecastModel.forecast.forecastDay[0].day.averageTemperatureInCelsius}")
        tvLocationName.text = weatherForecastModel.location.name
        tvTemperatureDegrees.text = weatherForecastModel.current.temperatureInCelsius.toString()

        val forecastAdapter = ForecastAdapter(weatherForecastModel.forecast.forecastDay, ForecastDelegate(1))
        forecastAdapter.notifyDataSetChanged()
        rvDailyForecast.adapter = forecastAdapter
        rvDailyForecast.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
    }

    override fun onForecastFailure(error: String) {
        println("onForecastFailure $error")
    }
}
