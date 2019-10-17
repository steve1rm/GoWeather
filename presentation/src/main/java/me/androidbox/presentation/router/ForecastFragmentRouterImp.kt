package me.androidbox.presentation.router

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import me.androidbox.presentation.R
import me.androidbox.presentation.forecast.ForecastActivity
import me.androidbox.presentation.forecast.ForecastFragment

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
}
