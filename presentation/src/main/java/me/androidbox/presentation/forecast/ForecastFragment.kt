package me.androidbox.presentation.forecast

import android.os.Bundle
import android.os.Parcelable
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
import me.androidbox.presentation.di.ForecastFragmentComponent
import me.androidbox.presentation.forecast.mvvm.ForecastViewModel
import me.androidbox.presentation.models.WeatherForecast
import org.parceler.Parcels
import javax.inject.Inject

class ForecastFragment : BaseFragment<ForecastViewModel>() {

    @Inject
    lateinit var forecastAdapter: ForecastAdapter

    private fun displayWeather(weatherForecast: WeatherForecast) {
        tvLocationName.text = weatherForecast.location.name
        val temperatureWithDegrees = "${weatherForecast.current.temperatureInCelsius}\u00B0"
        tvTemperatureDegrees.text = temperatureWithDegrees

        forecastAdapter.populate(weatherForecast.forecast.forecastDay)
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
            constraintSet1.clone(weather_forecast)
            val constraintSet2 = ConstraintSet()
            constraintSet2.clone(activity, R.layout.weather_forecast_slide)
            val transition = ChangeBounds()
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
            val parcelable = it.getParcelable<Parcelable>(ForecastActivity.WEATHER_FORECAST_KEY)
            val weatherForecast = Parcels.unwrap<WeatherForecast>(parcelable)
            displayWeather(weatherForecast)
            startSlideUpAnimation()
        } ?: run {
            Toast.makeText(activity, getString(R.string.failedToDisplayData), Toast.LENGTH_LONG).show()
        }
    }

    override fun injectDependencies(forecastFragmentComponent: ForecastFragmentComponent) {
        forecastFragmentComponent.inject(this)
    }
}
