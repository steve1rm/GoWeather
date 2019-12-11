package me.androidbox.presentation.forecast

import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import kotlinx.android.synthetic.main.weather_forecast.*
import kotlinx.android.synthetic.main.weather_forecast_header.*
import me.androidbox.presentation.R
import me.androidbox.presentation.adapters.ForecastAdapter
import me.androidbox.presentation.base.BaseFragment
import me.androidbox.presentation.di.forecast.ForecastFragmentComponent
import me.androidbox.presentation.forecast.mvp.ForecastPresenter
import me.androidbox.presentation.forecast.mvp.ForecastView
import me.androidbox.presentation.forecast.mvvm.ForecastViewModel
import me.androidbox.presentation.models.WeatherForecast
import javax.inject.Inject

class ForecastFragment(private val onFetchWeatherForecastFailure: () -> Unit)
    : BaseFragment<ForecastViewModel>(), ForecastView {

    @Inject
    lateinit var forecastAdapter: ForecastAdapter

    @Inject
    lateinit var forecastPresenter: ForecastPresenter

    @Inject
    lateinit var forecastViewModel: ForecastViewModel


    override fun onForecastSuccess(weatherForecast: WeatherForecast) {
        displayWeather(weatherForecast)
        startSlideUpAnimation()
    }

    override fun onForecastFailure(error: String) {
        onFetchWeatherForecastFailure()
    }

    private fun displayWeather(weatherForecast: WeatherForecast) {
        tvLocationName.text = weatherForecast.cityName
        val temperatureWithDegrees = "${weatherForecast.forecast[0].temp}\u00B0"
        tvTemperatureDegrees.text = temperatureWithDegrees

        forecastAdapter.populate(weatherForecast.forecast)
        val forecastAdapter = forecastAdapter
        forecastAdapter.notifyDataSetChanged()
        rvDailyForecast.adapter = forecastAdapter
        rvDailyForecast.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        rvDailyForecast.addItemDecoration(DividerItemDecoration(activity, RecyclerView.VERTICAL))
    }

    /* waits for the screen to fully render before applying the animation */
    private fun startSlideUpAnimation() {
        weather_forecast.post {
            val constraintSet1 = ConstraintSet()
            val constraintSet2 = ConstraintSet()
            val transition = ChangeBounds()

            constraintSet1.clone(weather_forecast)
            constraintSet2.clone(activity, R.layout.weather_forecast_slide)
            transition.duration = 500
            transition.interpolator = AccelerateDecelerateInterpolator()
            TransitionManager.beginDelayedTransition(weather_forecast, transition)
            constraintSet2.applyTo(weather_forecast)
        }
    }

    override fun provideLayoutId() =
        R.layout.weather_forecast

    override fun setupView(view: View, savedInstanceState: Bundle?) {
        val bundle = arguments

        bundle?.let {
            val latitude = bundle.getDouble(ForecastActivity.WEATHER_LATITUDE_KEY)
            val longitude = bundle.getDouble(ForecastActivity.WEATHER_LONGITUDE_KEY)

            forecastPresenter.initialize(this)
            forecastPresenter.requestWeatherForecast(latitude, longitude, 16)

        } ?: Toast.makeText(activity, getString(R.string.failedToDisplayData), Toast.LENGTH_LONG).show()

    }

    override fun injectDependencies(forecastFragmentComponent: ForecastFragmentComponent) {
        forecastFragmentComponent.inject(this)
    }
}
