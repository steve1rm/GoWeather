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
import me.androidbox.presentation.forecast.mvp.ForecastPresenter
import me.androidbox.presentation.forecast.mvp.ForecastView
import me.androidbox.presentation.forecast.mvvm.ForecastViewModel
import me.androidbox.presentation.models.WeatherForecast
import org.parceler.Parcels
import javax.inject.Inject

class ForecastActivity : BaseActivity<ForecastViewModel>(), ForecastView, RetryListener, LocationUtilsListener {

    companion object {
        const val WEATHER_FORECAST_KEY = "weatherForecast"
    }

    @Inject
    lateinit var forecastPresenter: ForecastPresenter

    @Inject
    lateinit var location: LocationUtils

    private var fragmentManager: FragmentManager? = null

    override fun provideLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun setupView(savedInstanceState: Bundle?) {
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

    override fun setupObservers() {
        super.setupObservers()
        viewModel.data.observe(this@ForecastActivity, Observer {
            println(it)
        })
    }

    override fun injectDependencies(forecastActivityComponent: ForecastActivityComponent) =
        forecastActivityComponent.inject(this@ForecastActivity)

  /*  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        DaggerForecastActivityComponent
            .builder()
            .forecastActivityModule(ForecastActivityModule(this@ForecastActivity))
            .goWeatherComponent((application as GoWeatherApplication).component)
            .build()
            .inject(this@ForecastActivity)

  *//*      fragmentManager = supportFragmentManager
        forecastPresenter.initialize(this)

        location.setLocationListener(this)
        if(location.isLocationServicesEnabled(this)) {
            startLoadingFragment()
            location.getLocationCoordinates(this)
        }
        else {
            displayLocationSettings()
            startRetryFragment()
        }*//*
    }*/

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
            fragmentTransaction.replace(R.id.forecastActivityContainer, RetryFragment(onRetry), "RetryFragment")
            fragmentTransaction.commit()
        }
    }

    override fun onForecastSuccess(weatherForecast: WeatherForecast) {
        val bundle = Bundle()
        val parcelable = Parcels.wrap(weatherForecast)
        bundle.putParcelable(WEATHER_FORECAST_KEY, parcelable)
        val forecastFragment = ForecastFragment()
        forecastFragment.arguments = bundle

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.forecastActivityContainer, forecastFragment, "ForecastFragment")
        fragmentTransaction.commit()
    }

    override fun onForecastFailure(error: String) {
        startRetryFragment()
    }

    private val onRetry: () -> Unit = {
        if(location.isLocationServicesEnabled(this)) {
            startLoadingFragment()
            location.getLocationCoordinates(this)
        }
        else {
            displayLocationSettings()
            startRetryFragment()
        }
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
        forecastPresenter.requestWeatherForecast(latitude, longitude, 5)
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
