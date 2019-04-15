package me.androidbox.presentation.forecast

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.AndroidInjection
import me.androidbox.presentation.R
import me.androidbox.presentation.models.WeatherForecast
import org.parceler.Parcels
import javax.inject.Inject

class ForecastActivity : AppCompatActivity(), ForecastView {

    @Inject
    lateinit var forecastPresenter: ForecastPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        startFragment()
        forecastPresenter.initialize(this)
        forecastPresenter.requestWeatherForecast()
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
}
