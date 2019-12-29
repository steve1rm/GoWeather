package me.androidbox.presentation.router

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import me.androidbox.presentation.R
import me.androidbox.presentation.forecast.ForecastActivity
import me.androidbox.presentation.forecast.ForecastFragment
import me.androidbox.presentation.models.CurrentWeather
import me.androidbox.presentation.models.WeatherForecast
import org.parceler.Parcels

class ForecastFragmentRouterImp(private val fragmentManager: FragmentManager) : ForecastFragmentRouter {

    override fun goToForecastFragment(latitude: Double, longitude: Double, statusCallback: () -> Unit) {
        val bundle = Bundle()
        bundle.putDouble(ForecastActivity.WEATHER_LATITUDE_KEY, latitude)
        bundle.putDouble(ForecastActivity.WEATHER_LONGITUDE_KEY, longitude)

        val forecastFragment = ForecastFragment(statusCallback)
        forecastFragment.arguments = bundle

        fragmentManager.let {
            val fragmentTransaction = it.beginTransaction()
            fragmentTransaction.replace(R.id.forecastActivityContainer, forecastFragment, "ForecastFragment")
            fragmentTransaction.commit()
        }
    }

    override fun goToForecastFragment(weatherForecast: WeatherForecast, currentWeather: CurrentWeather, statusCallback: () -> Unit) {
        val forecastFragment = ForecastFragment(statusCallback)

        forecastFragment.arguments = Bundle().let {
            it.putParcelable(ForecastActivity.WEATHER_FORECAST_KEY, Parcels.wrap(weatherForecast))
            it.putParcelable(ForecastActivity.CURRENT_WEATHER_KEY, Parcels.wrap(currentWeather))
            it
        }

        fragmentManager.run {
            val fragmentTransaction = beginTransaction()
            fragmentTransaction.replace(R.id.forecastActivityContainer, forecastFragment, "ForecastFragment")
            fragmentTransaction.commit()
        }
    }
}
