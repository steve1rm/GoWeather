package me.androidbox.presentation.forecast

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.weather_forecast.*
import kotlinx.android.synthetic.main.weather_forecast_header.*
import me.androidbox.presentation.R
import me.androidbox.presentation.adapters.ForecastAdapter
import me.androidbox.presentation.adapters.ForecastDelegate
import me.androidbox.presentation.models.WeatherForecast
import org.parceler.Parcels

class ForecastFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AndroidSupportInjection.inject(this)
        return inflater.inflate(R.layout.weather_forecast, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        val parcelable = bundle?.getParcelable<Parcelable>("weatherForecast")
        val weatherForecast = Parcels.unwrap<WeatherForecast>(parcelable)
        displayWeather(weatherForecast)
    }

     fun displayWeather(weatherForecast: WeatherForecast) {
        println("displayWeather ${weatherForecast.forecast.forecastDay[0].day.averageTemperatureInCelsius}")
        tvLocationName.text = weatherForecast.location.name
        val temperatureWithDegrees = "${weatherForecast.current.temperatureInCelsius}\u00B0"
        tvTemperatureDegrees.text = temperatureWithDegrees

        val forecastAdapter = ForecastAdapter(weatherForecast.forecast.forecastDay, ForecastDelegate(1))
        forecastAdapter.notifyDataSetChanged()
        rvDailyForecast.adapter = forecastAdapter
        rvDailyForecast.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        rvDailyForecast.addItemDecoration(DividerItemDecoration(activity, RecyclerView.VERTICAL))
    }
}
