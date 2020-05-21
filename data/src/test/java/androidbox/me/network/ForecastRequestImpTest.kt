package androidbox.me.network

import androidbox.me.mappers.ForecastRequestDomainMapper
import androidbox.me.mappers.ForecastRequestEntityMapper
import com.nhaarman.mockitokotlin2.mock
import me.androidbox.interactors.forecast.WeatherForecast
import javax.inject.Inject

class ForecastRequestImpTest {

    private lateinit var forecastRequest: WeatherForecast
    private val weatherForecastService: WeatherForecastService = mock()
    private val apiKey = "apikey"

    @Inject
    lateinit var forecastRequestEntityMapper: ForecastRequestEntityMapper

    @Inject
    lateinit var forecastRequestDomainMapper: ForecastRequestDomainMapper

    /** TODO fix test */

/*
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
                WeatherForecastModel(
                    LocationEntity("", "", ""),
                    CurrentWeatherEntity(45.5F),
                    ForecastEntity(emptyList())
                )
            ))

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
*/
}
