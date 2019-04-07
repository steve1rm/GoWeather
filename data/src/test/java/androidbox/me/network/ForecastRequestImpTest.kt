package androidbox.me.network

import androidbox.me.entities.*
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class ForecastRequestImpTest {

    private lateinit var forecastRequest: ForecastRequest
    private val weatherForecastService: WeatherForecastService = mock()
    private val apiKey = "apikey"

    @Before
    fun setup() {
        forecastRequest = ForecastRequestImp(weatherForecastService, apiKey)
        assertThat(forecastRequest).isNotNull
    }

    @Test
    fun `should request a 5 day forecast`() {
        whenever(weatherForecastService.forecast(apiKey, "34.858585,58.345345", 5))
            .thenReturn(Single.just(
                WeatherForecastEntity(
                    LocationEntity("", "", ""),
                    CurrrentEntity(45.5F),
                    ForecastEntity(ForecastDayEntity(emptyList())))))

        forecastRequest.getWeatherForecast(34.858585F, 58.345345F, 5)
            .test()
            .assertNoErrors()
            .assertValueCount(1)
            .assertComplete()

        verify(weatherForecastService).forecast(apiKey, "34.858585,58.345345", 5)
        verifyNoMoreInteractions(weatherForecastService)
    }

    @Test
    fun `should throw runtime exception when error occurs`() {
        whenever(weatherForecastService.forecast(apiKey, "34.858585,58.345345", 5))
            .thenReturn(Single.error(RuntimeException("runtime exception")))

        forecastRequest.getWeatherForecast(34.858585F, 58.345345F, 5)
            .test()
            .assertErrorMessage("runtime exception")

        verify(weatherForecastService).forecast(apiKey, "34.858585,58.345345", 5)
        verifyNoMoreInteractions(weatherForecastService)
    }
}
