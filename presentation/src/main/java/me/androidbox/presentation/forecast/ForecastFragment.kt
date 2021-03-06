package me.androidbox.presentation.forecast

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.weather_forecast.*
import kotlinx.android.synthetic.main.weather_forecast_header.*
import me.androidbox.presentation.R
import me.androidbox.presentation.adapters.ForecastAdapter
import me.androidbox.presentation.models.WeatherForecast
import org.parceler.Parcels
import javax.inject.Inject

class ForecastFragment : Fragment() {

    @Inject
    lateinit var forecastAdapter: ForecastAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AndroidSupportInjection.inject(this)
        return inflater.inflate(R.layout.weather_forecast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
}
