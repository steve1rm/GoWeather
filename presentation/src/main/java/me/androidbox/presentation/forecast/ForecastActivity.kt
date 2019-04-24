package me.androidbox.presentation.forecast

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentManager
import com.google.android.gms.location.*
import com.google.android.gms.tasks.OnCanceledListener
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.Task
import dagger.android.AndroidInjection
import me.androidbox.presentation.R
import me.androidbox.presentation.models.WeatherForecast
import org.parceler.Parcels
import java.lang.Exception
import javax.inject.Inject
import kotlin.properties.Delegates

class ForecastActivity : AppCompatActivity(), ForecastView, RetryListener {

    @Inject
    lateinit var forecastPresenter: ForecastPresenter

    private var fusedLocationProviderClient: FusedLocationProviderClient by Delegates.notNull()
    private var fragmentManager: FragmentManager? = null
    private val permissionRequestCode = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        fragmentManager = supportFragmentManager
        forecastPresenter.initialize(this)

        if(isLocationServicesEnabled()) {
            getLocationFused()
            startLoadingFragment()
        }
        else {
            displaySettings()
            startRetryFragment()
        }
    }

    private fun getLocationFused() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                permissionRequestCode
            )
        } else {
            fusedLocationProviderClient.lastLocation.addOnFailureListener(this) {
                println(it.message)
            }.addOnCanceledListener(this) {
                println("Cancelled")
            }.addOnSuccessListener(this) { location ->
                if (location != null) {
                    forecastPresenter.requestWeatherForecast(location.latitude, location.longitude)
                } else {
                    val locationCallback = object : LocationCallback() {
                        override fun onLocationResult(locationResult: LocationResult) {
                            forecastPresenter.requestWeatherForecast(
                                locationResult.lastLocation.latitude,
                                locationResult.lastLocation.longitude
                            )
                            fusedLocationProviderClient.removeLocationUpdates(this)
                        }
                    }

                    val locationResult = LocationRequest()
                    locationResult.maxWaitTime = 10000
                    fusedLocationProviderClient.requestLocationUpdates(locationResult, locationCallback, null)
                        .addOnCanceledListener {
                            println()
                        }.addOnFailureListener {
                            println(it.message)
                        }
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            permissionRequestCode -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fusedLocationProviderClient.lastLocation.addOnSuccessListener(this) { location ->
                        if(location != null) {
                            forecastPresenter.requestWeatherForecast(location.latitude, location.longitude)
                        }
                    }
                }
                else {
                    Toast.makeText(this, "Permissions has been denied", Toast.LENGTH_LONG).show()
                    startRetryFragment()
                }
            }
        }
    }

    private fun isLocationServicesEnabled(): Boolean {
        return (getSystemService(Context.LOCATION_SERVICE) as LocationManager)
            .isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    private fun displaySettings() {
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

    override fun onRetry() {
        if(isLocationServicesEnabled()) {
            getLocationFused()
            startLoadingFragment()
        }
        else {
            displaySettings()
            startRetryFragment()
        }
    }

    override fun onForecastFailure(error: String) {
        startRetryFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
