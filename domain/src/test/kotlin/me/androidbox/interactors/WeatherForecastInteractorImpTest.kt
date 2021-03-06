package me.androidbox.interactors

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import me.androidbox.models.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class WeatherForecastInteractorImpTest {

    private val weatherForecast: WeatherForecast = mock()

    private lateinit var weatherForecastInteractor: WeatherForecastInteractor

    @Before
    fun setup() {
        weatherForecastInteractor = WeatherForecastInteractorImp(weatherForecast)
        assertThat(weatherForecastInteractor).isNotNull
    }

    @Test
    fun `should request weather forecast`() {
        whenever(weatherForecast.requestWeatherForecast(ForecastRequestModel(0.0, 0.0, 1)))
            .thenReturn(Single.just(WeatherForecastModel(
                LocationModel("", "", ""),
                CurrentModel(0),
                ForecastModel(emptyList()))))

        weatherForecastInteractor.requestWeatherForecast(ForecastRequestModel(0.0, 0.0, 1))
            .test()
            .assertValueCount(1)
            .assertNoErrors()

        verify(weatherForecast).requestWeatherForecast(ForecastRequestModel(0.0, 0.0, 1))
    }
}
