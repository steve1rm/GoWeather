package me.androidbox.interactors

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
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
        whenever(weatherForecast.requestWeatherForecast(ForecastRequestModel(0F, 0F, 1)))
            .thenReturn(Single.just(WeatherForecastModel(LocationModel("", "", ""), CurrentModel(0F), ForecastModel(
                ForecastDayModel(emptyList())
            ))))

        weatherForecastInteractor.requestWeatherForecast(ForecastRequestModel(0F, 0F, 1))
            .test()
            .assertValueCount(1)
            .assertNoErrors()

        verify(weatherForecast).requestWeatherForecast(ForecastRequestModel(0F, 0F, 1))
    }
}
