package me.androidbox.presentation.forecast

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.android.AndroidInjection
import me.androidbox.presentation.R
import me.androidbox.presentation.models.WeatherForecast
import org.parceler.Parcels
import javax.inject.Inject
import kotlin.properties.Delegates


class ForecastActivity : AppCompatActivity(), ForecastView, LocationListener {

    @Inject
    lateinit var forecastPresenter: ForecastPresenter

    private var fusedLocationProviderClient: FusedLocationProviderClient by Delegates.notNull()
    private var wayLatitude = 0.0
    private var wayLongtitude = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if(isLocationServicesEnabled()) {
            getLocationFused()
            startFragment()
            forecastPresenter.initialize(this)
            forecastPresenter.requestWeatherForecast()
        }
        else {
            displaySettings()
        }
    }

    private fun getLocationFused() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION), 1000)
        }
        else {
            fusedLocationProviderClient.lastLocation.addOnSuccessListener(this) { location ->
                if(location != null) {
                    wayLatitude = location.latitude
                    wayLongtitude = location.longitude

                    println("latitude $wayLatitude")
                    println("longtitude $wayLongtitude")
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            1000 -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fusedLocationProviderClient.lastLocation.addOnSuccessListener(this) { location ->
                        if(location != null) {
                            wayLatitude = location.latitude
                            wayLongtitude = location.longitude

                            println("latitude $wayLatitude")
                            println("longtitude $wayLongtitude")
                        }
                    }
                }
                else {
                    println("Permission denied")
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val criteria = Criteria()
        val provider = locationManager.getBestProvider(criteria, false)
        val location = locationManager.getLastKnownLocation(provider)


        if(location != null) {
            onLocationChanged(location)
        }
    }

    private fun isLocationServicesEnabled(): Boolean {
        return (getSystemService(Context.LOCATION_SERVICE) as LocationManager)
            .isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    private fun displaySettings() {
        startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
    }

    private fun startFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.forecastActivityContainer, LoadingFragment(), "ForecastFragment")
        fragmentTransaction.commit()
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
        /* change to failure */
    }

    override fun onLocationChanged(location: Location?) {
        println(location?.latitude)
        println(location?.longitude)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        println("onStatusChanged $provider")
    }

    override fun onProviderEnabled(provider: String?) {
        println("onProviderEnabled $provider")
    }

    override fun onProviderDisabled(provider: String?) {
        println("onProviderDisabled $provider")
    }
}
