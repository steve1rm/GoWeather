package androidbox.me.network

import androidbox.me.entities.*
import androidbox.me.mappers.ForecastRequestDomainMapper
import androidbox.me.mappers.ForecastRequestEntityMapper
import androidbox.me.network.di.DaggerTestDataComponent
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import me.androidbox.interactors.WeatherForecast
import me.androidbox.models.ForecastRequestModel
import me.androidbox.models.ForecastRequestModelBuilder
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

class ForecastRequestImpTest {

    private lateinit var forecastRequest: WeatherForecast
    private val weatherForecastService: WeatherForecastService = mock()
    private val apiKey = "apikey"

    @Inject
    lateinit var forecastRequestEntityMapper: ForecastRequestEntityMapper

    @Inject
    lateinit var forecastRequestDomainMapper: ForecastRequestDomainMapper

    @Before
    fun setup() {
        DaggerTestDataComponent
            .builder()
            .build()
            .inject(this)

        forecastRequest = ForecastRequestImp(
            weatherForecastService,
            apiKey,
            forecastRequestEntityMapper,
            forecastRequestDomainMapper)

        assertThat(forecastRequest).isNotNull
    }


    @Test
    fun `should request a 5 day forecast`() {
        whenever(weatherForecastService.forecast(apiKey, "34.858585,58.345345", 5))
            .thenReturn(Single.just(
                WeatherForecastEntity(
                    LocationEntity("", "", ""),
                    CurrrentEntity(45.5F),
                    ForecastEntity(emptyList()))))

        forecastRequest.requestWeatherForecast(createForecastModel())
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

        forecastRequest.requestWeatherForecast(createForecastModel())
            .test()
            .assertErrorMessage("runtime exception")

        verify(weatherForecastService).forecast(apiKey, "34.858585,58.345345", 5)
        verifyNoMoreInteractions(weatherForecastService)
    }

    private fun createForecastModel(): ForecastRequestModel {
        return forecastModel {
            latitude = 34.858585F
            longitude = 58.345345F
            days = 5
        }
    }

    private fun forecastModel(func: ForecastRequestModelBuilder.() -> Unit): ForecastRequestModel {
        return ForecastRequestModelBuilder().apply(func).build()
    }
}
