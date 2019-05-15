package me.androidbox.presentation.forecast

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import dagger.android.AndroidInjection
import me.androidbox.presentation.R
import me.androidbox.presentation.common.LocationUtils
import me.androidbox.presentation.common.LocationUtilsListener
import me.androidbox.presentation.models.WeatherForecast
import org.parceler.Parcels
import javax.inject.Inject

class ForecastActivity : AppCompatActivity(), ForecastView, RetryListener, LocationUtilsListener {

    @Inject
    lateinit var forecastPresenter: ForecastPresenter

    @Inject
    lateinit var location: LocationUtils

    private var fragmentManager: FragmentManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        fragmentManager = supportFragmentManager
        forecastPresenter.initialize(this)

        location.setLocationListener(this)
        if(location.isLocationServicesEnabled(this)) {
            startLoadingFragment()
            location.getLocationCoordinates(this)
        }
        else {
            displayLocationSettings()
            startRetryFragment()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        location.requestPermissionResults(this, requestCode, permissions, grantResults)
    }

    private fun displayLocationSettings() {
        Toast.makeText(this, "Please enable location on your device - and try again", Toast.LENGTH_LONG).show()
        startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
    }

    private fun startLoadingFragment() {
        fragmentManager?.let {
            val fragmentTransaction = it.beginTransaction()
            fragmentTransaction.replace(R.id.forecastActivityContainer, LoadingFragment(), "LoadingFragment")
            fragmentTransaction.commit()
        }
    }

    private fun startRetryFragment() {
        fragmentManager?.let {
            val fragmentTransaction = it.beginTransaction()
            fragmentTransaction.replace(R.id.forecastActivityContainer, RetryFragment(), "RetryFragment")
            fragmentTransaction.commit()
        }
    }

    override fun onForecastSuccess(weatherForecast: WeatherForecast) {
        val bundle = Bundle()
        val parcelable = Parcels.wrap(weatherForecast)
        bundle.putParcelable("weatherForecast", parcelable)
        val forecastFragment = ForecastFragment()
        forecastFragment.arguments = bundle

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.forecastActivityContainer, forecastFragment, "ForecastFragment")
        fragmentTransaction.commit()
    }

    override fun onForecastFailure(error: String) {
        startRetryFragment()
    }

    override fun onRetry() {
        if(location.isLocationServicesEnabled(this)) {
            startLoadingFragment()
            location.getLocationCoordinates(this)
        }
        else {
            displayLocationSettings()
            startRetryFragment()
        }
    }

    override fun onLocationSuccess(latitude: Double, longitude: Double) {
        forecastPresenter.requestWeatherForecast(latitude, longitude)
    }

    override fun onLocationFailure(message: String) {
        startRetryFragment()
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        location.removeLocationListener()
        super.onDestroy()
    }
}
