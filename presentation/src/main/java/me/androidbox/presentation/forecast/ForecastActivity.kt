package me.androidbox.presentation.forecast

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import me.androidbox.presentation.R
import me.androidbox.presentation.base.BaseActivity
import me.androidbox.presentation.common.LocationUtils
import me.androidbox.presentation.common.LocationUtilsListener
import me.androidbox.presentation.di.ForecastActivityComponent
import me.androidbox.presentation.forecast.mvvm.ForecastViewModel
import me.androidbox.presentation.router.*
import javax.inject.Inject

class ForecastActivity : BaseActivity<ForecastViewModel>(), LocationUtilsListener {

    companion object {
        const val WEATHER_LATITUDE_KEY = "weatherLatitudeKey"
        const val WEATHER_LONGITUDE_KEY = "weatherLongitudeKey"
    }

    @Inject
    lateinit var location: LocationUtils

    @Inject
    lateinit var forecastFragmentRouter: ForecastFragmentRouter

    @Inject
    lateinit var retryFragmentRouter: RetryFragmentRouter

    @Inject
    lateinit var loadingFragmentRouter: LoadingFragmentRouter

    private fun displayLocationSettings() {
        Toast.makeText(this, "Please enable location on your device - and try again", Toast.LENGTH_LONG).show()
        startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
    }

    private fun startLoadingFragment() {
        loadingFragmentRouter.gotoLoadingFragment()
    }

    private fun startRetryFragment() {
        retryFragmentRouter.gotoRetryFragment(::onRetry)
    }

    private fun startForecastFragment(latitude: Double, longitude: Double) {
        forecastFragmentRouter.goToForecastFragment(latitude, longitude, ::onWeatherForecastFetchingFailure)
    }

    private fun onRetry() {
        if(location.isLocationServicesEnabled(this)) {
            startLoadingFragment()
            location.getLocationCoordinates(this)
        }
        else {
            displayLocationSettings()
            startRetryFragment()
        }
    }

    private fun onWeatherForecastFetchingFailure() {
        startRetryFragment()
    }

    override fun onLocationSuccess(latitude: Double, longitude: Double) {
        startForecastFragment(latitude, longitude)
    }

    override fun onLocationFailure(message: String) {
        startRetryFragment()
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        location.removeLocationListener()
        super.onDestroy()
    }

    override fun provideLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun setupView(savedInstanceState: Bundle?) {
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

    override fun setupObservers() {
        super.setupObservers()
        viewModel.data.observe(this@ForecastActivity, Observer {
            println(it)
        })
    }

    override fun injectDependencies(forecastActivityComponent: ForecastActivityComponent) =
        forecastActivityComponent.inject(this@ForecastActivity)

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        location.requestPermissionResults(this, requestCode, permissions, grantResults)
    }
}
