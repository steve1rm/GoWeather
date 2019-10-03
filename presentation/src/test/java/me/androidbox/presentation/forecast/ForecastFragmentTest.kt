package me.androidbox.presentation.forecast

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.testing.launchFragment
import androidx.test.core.app.ApplicationProvider
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
import org.robolectric.shadows.ShadowToast

@RunWith(AndroidJUnit4::class)
class ForecastFragmentTest {

    private lateinit var forecastFragment: ForecastFragment

    @Before
    fun setUp() {
        forecastFragment = ForecastFragment {}
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

    @Test
    fun `should not display any temperature details if the bundle is null`() {
        val bundle: Bundle? = null
        forecastFragment.arguments = bundle

        launchFragment<ForecastFragment>(bundle).onFragment {
            assertThat(it.tvLocationName.text).isBlank()
            assertThat(it.tvTemperatureDegrees.text).isBlank()
            assertThat(ShadowToast.getTextOfLatestToast())
                .isEqualTo(ApplicationProvider.getApplicationContext<Context>().resources.getString(R.string.failedToDisplayData))
        }
    }

    private fun createForecastList(): List<ForecastDay> {
        return listOf(
            ForecastDay("date", "dateEpoch", Day(45F)),
            ForecastDay("date", "dateEphoc", Day(36F)))
    }
}
