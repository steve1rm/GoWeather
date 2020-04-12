package me.androidbox.presentation.forecast

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.core.os.BuildCompat
import androidx.lifecycle.Observer
import me.androidbox.presentation.R
import me.androidbox.presentation.base.BaseActivity
import me.androidbox.presentation.common.LocationUtils
import me.androidbox.presentation.common.LocationUtilsImp.LocationStatus
import me.androidbox.presentation.di.forecast.ForecastActivityComponent
import me.androidbox.presentation.forecast.mvp.ForecastPresenter
import me.androidbox.presentation.forecast.mvp.ForecastView
import me.androidbox.presentation.forecast.mvvm.ForecastViewModel
import me.androidbox.presentation.models.CurrentWeather
import me.androidbox.presentation.models.WeatherForecast
import me.androidbox.presentation.router.ForecastFragmentRouter
import me.androidbox.presentation.router.LoadingFragmentRouter
import me.androidbox.presentation.router.RetryFragmentRouter
import me.androidbox.presentation.utils.EspressoIdlingResource
import javax.inject.Inject

class ForecastActivity : BaseActivity<ForecastViewModel>(), ForecastView {

    companion object {
        const val WEATHER_LATITUDE_KEY = "weatherLatitudeKey"
        const val WEATHER_LONGITUDE_KEY = "weatherLongitudeKey"
        const val WEATHER_FORECAST_KEY = "weatherforecastkey"
        const val CURRENT_WEATHER_KEY = "currentWeatherkey"
    }

    @Inject
    lateinit var location: LocationUtils

    @Inject
    lateinit var forecastFragmentRouter: ForecastFragmentRouter

    @Inject
    lateinit var retryFragmentRouter: RetryFragmentRouter

    @Inject
    lateinit var loadingFragmentRouter: LoadingFragmentRouter

    @Inject
    lateinit var forecastPresenter: ForecastPresenter

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

    private fun startForecastFragment(weatherForecast: WeatherForecast, currentWeather: CurrentWeather) {
        forecastFragmentRouter.goToForecastFragment(weatherForecast, currentWeather, ::onWeatherForecastFetchingFailure)

    }

    private fun onRetry() {
        if(location.isLocationServicesEnabled()) {
            startLoadingFragment()
            location.getLocationCoordinates(::onLocationResult)
        }
        else {
            displayLocationSettings()
            startRetryFragment()
        }
    }

    private fun onWeatherForecastFetchingFailure() {
        startRetryFragment()
    }

    override fun provideLayoutId(): Int {
        return R.layout.activity_home
    }

    private fun onLocationResult(locationStatus: LocationStatus) {
        when(locationStatus) {
            is LocationStatus.Success -> {
                forecastPresenter.initialize(this)
                forecastPresenter.requestForecastAndCurrentWeather(locationStatus.latitude, locationStatus.longitude, 20)
            }
            is LocationStatus.Failure -> {
                Toast.makeText(this, locationStatus.errorMessage, Toast.LENGTH_LONG).show()
                startRetryFragment()
            }
        }
    }

    override fun setupView(savedInstanceState: Bundle?) {
        if(location.isLocationServicesEnabled()) {
            startLoadingFragment()
            location.getLocationCoordinates(::onLocationResult)
        }
        else {
            displayLocationSettings()
            startRetryFragment()
        }
    }

    override fun onForecastSuccess(weatherForecast: WeatherForecast, currentWeather: CurrentWeather) {
        EspressoIdlingResource.increment()
        startForecastFragment(weatherForecast, currentWeather)
    }

    override fun onForecastFailure(error: String) {
        startRetryFragment()
        EspressoIdlingResource.decrement()
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
        location.requestPermissionResults(::onLocationResult, requestCode, permissions, grantResults)
    }
}
