package androidbox.me.network

import androidbox.me.entities.CurrrentEntity
import androidbox.me.entities.ForecastEntity
import androidbox.me.entities.LocationEntity
import androidbox.me.entities.WeatherForecastEntity
import androidbox.me.mappers.ForecastRequestDomainMapper
import androidbox.me.mappers.ForecastRequestEntityMapper
import androidbox.me.network.di.DaggerTestDataComponent
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import me.androidbox.interactors.WeatherForecast
import me.androidbox.models.ForecastRequestModel
import me.androidbox.models.ForecastRequestModelBuilder
import okhttp3.HttpUrl
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import java.io.File
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

    @Test
    fun `test the response from mock web server`() {
        val mockWebServer = MockWebServer()
        mockWebServer.enqueue(MockResponse().setBody(getJson("json/fivedayforecast.json")))
        mockWebServer.start()

        val baseUrl = mockWebServer.url("json/fivedayforecast.json")
        val requestBody = sendRequest(OkHttpClient(), baseUrl)
        assertThat(requestBody).isEqualToIgnoringCase(getJson("json/fivedayforecast.json"))
    }

    private fun sendRequest(okHttpClient: OkHttpClient, base: HttpUrl): String {
        val body = RequestBody.create(MediaType.parse("text/plain"), "content")

        val request = okhttp3.Request.Builder()
            .post(body)
            .url(base)
            .build()

        val response = okHttpClient.newCall(request).execute()

        return response.body()?.string() ?: "not found"
    }

    private fun getJson(path: String): String {
        val url = this.javaClass.classLoader.getResource(path)
        val file = File(url.path)
        return String(file.readBytes())
    }

    private fun createForecastModel(): ForecastRequestModel {
        return forecastModel {
            latitude = 34.858585
            longitude = 58.345345
            days = 5
        }
    }

    private fun forecastModel(func: ForecastRequestModelBuilder.() -> Unit): ForecastRequestModel {
        return ForecastRequestModelBuilder().apply(func).build()
    }
}
