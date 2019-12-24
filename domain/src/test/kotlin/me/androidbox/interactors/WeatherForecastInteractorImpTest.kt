package me.androidbox.interactors

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import me.androidbox.interactors.forecast.WeatherForecast
import me.androidbox.interactors.forecast.WeatherForecastInteractor
import me.androidbox.interactors.forecast.WeatherForecastInteractorImp
import me.androidbox.models.request.ForecastRequestModel
import me.androidbox.models.response.WeatherForecastModel
import me.androidbox.models.response.ForecastModel
import me.androidbox.models.response.LocationModel
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class WeatherForecastInteractorImpTest {

    private val weatherForecast: WeatherForecast = mock()

    private lateinit var weatherForecastInteractor: WeatherForecastInteractor

    @Before
    fun setup() {
        weatherForecastInteractor =
            WeatherForecastInteractorImp(
                weatherForecast
            )
        assertThat(weatherForecastInteractor).isNotNull
    }

    @Test
    fun `should request weather forecast`() {
        whenever(weatherForecast.requestWeatherForecast(
            ForecastRequestModel(
                0.0,
                0.0,
                1
            )
        ))
            .thenReturn(Single.just(
                WeatherForecastModel(
                    LocationModel("", "", ""),
                    CurrentModel(0),
                    ForecastModel(emptyList())
                )
            ))

        weatherForecastInteractor.requestWeatherForecast(
            ForecastRequestModel(
                0.0,
                0.0,
                1
            )
        )
            .test()
            .assertValueCount(1)
            .assertNoErrors()

        verify(weatherForecast).requestWeatherForecast(
            ForecastRequestModel(
                0.0,
                0.0,
                1
            )
        )
    }
}
