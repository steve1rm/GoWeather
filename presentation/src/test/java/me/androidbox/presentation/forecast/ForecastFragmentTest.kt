package me.androidbox.presentation.forecast

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.android.synthetic.main.weather_forecast.*
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
    fun `should assign correct temperature name and location`() {
        val bundle = Bundle()
        val weatherForecast = WeatherForecast(
            Location("name", "region", "country"),
            Current(42),
            Forecast(createForecastList()))
        val parcelable = Parcels.wrap(weatherForecast)

        bundle.putParcelable("weatherForecast", parcelable)
        forecastFragment.arguments = bundle

        launchFragment<ForecastFragment>(bundle).onFragment {
            assertThat(it.tvLocationName.text).isEqualTo("name")
            assertThat(it.tvTemperatureDegrees.text).isEqualTo("42\u00B0")
            assertThat(it.rvDailyForecast.visibility).isEqualTo(View.VISIBLE)
        }
    }

    private fun createForecastList(): List<ForecastDay> {
        return listOf(
            ForecastDay("date", "dateEpoch", Day(45F)),
            ForecastDay("date", "dateEphoc", Day(36F)))
    }
}