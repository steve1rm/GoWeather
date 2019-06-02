package me.androidbox.presentation.forecast

import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.android.synthetic.main.weather_forecast_header.*
import me.androidbox.presentation.R
import me.androidbox.presentation.models.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.parceler.Parcels

@RunWith(AndroidJUnit4::class)
class ForecastFragmentTest {

    private lateinit var forecastFragment: ForecastFragment

    @Before
    fun setUp() {
        forecastFragment = ForecastFragment()
        assertThat(forecastFragment).isNotNull
    }

    @Test
    fun `should test`() {
        val bundle = Bundle()
        val weatherForecast = WeatherForecast(
            Location("name", "region", "country"),
            Current(42),
            Forecast(createForecastList()))
        val parcelable = Parcels.wrap(weatherForecast)

        bundle.putParcelable("weatherForecast", parcelable)
        forecastFragment.arguments = bundle

        var tvTemperature: TextView? = null
        launchFragment<ForecastFragment>(bundle).onFragment {
            tvTemperature = it.tvTemperatureDegrees
        }

    //    tvTemperature = forecastFragment.activity?.findViewById<TextView>(R.id.tvTemperatureDegrees)
        assertThat(tvTemperature?.text).contains("42")
    }

    private fun createForecastList(): List<ForecastDay> {
        return listOf<ForecastDay>(
            ForecastDay("date", "dateEpoch", Day(45F)),
            ForecastDay("date", "dateEphoc", Day(36F)))
    }
}