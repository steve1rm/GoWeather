package me.androidbox.presentation.forecast

import android.os.Bundle
import androidx.test.ext.junit.runners.AndroidJUnit4
import me.androidbox.presentation.models.Current
import me.androidbox.presentation.models.Forecast
import me.androidbox.presentation.models.Location
import me.androidbox.presentation.models.WeatherForecast
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
            Forecast(emptyList()))
        val parcelable = Parcels.wrap(weatherForecast)

        bundle.putParcelable("weatherForecast", parcelable)
        forecastFragment.arguments = bundle
    }
}