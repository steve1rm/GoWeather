package me.androidbox.presentation.router

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import me.androidbox.presentation.R
import me.androidbox.presentation.di.scopes.ActivityScope
import me.androidbox.presentation.forecast.ForecastActivity
import me.androidbox.presentation.forecast.ForecastFragment
import javax.inject.Inject

@ActivityScope
class ForecastFragmentRouterImp @Inject constructor() : ForecastFragmentRouter {

    override fun goToForecastFragment(fragmentManager: FragmentManager, latitude: Double, longitude: Double, statusCallback: () -> Unit) {
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
