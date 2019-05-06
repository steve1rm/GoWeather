package me.androidbox.presentation.forecast

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import me.androidbox.presentation.R
import me.androidbox.presentation.di.DaggerAndroidTestGoWeatherPresentationComponent
import okhttp3.HttpUrl
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.nio.charset.Charset
import javax.inject.Inject
import javax.inject.Named

@RunWith(AndroidJUnit4::class)
class ForecastActivityAndroidTest {

    @field:[Inject Named("BaseUrl")]
    lateinit var baseUrl: String

    @get:Rule
    val activityRule = ActivityTestRule(ForecastActivity::class.java, false, false)

    private val mockWebServer = MockWebServer()

    @Before
    fun setUp() {
        DaggerAndroidTestGoWeatherPresentationComponent
            .builder()
            .build()
            .inject(this)

        mockWebServer.start()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun appLaunchedSuccessfully() {
        loadFromResources("json/fivedayforecast.json")
        mockWebServer.enqueue(MockResponse().setBody(loadFromResources("json/fivedayforecast.json")))

        ActivityScenario.launch(ForecastActivity::class.java)

        onView(withText(R.string.app_name))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.action_bar), hasDescendant(withText("GoWeather"))))
            .check(matches(isDisplayed()))
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

    private fun loadFromResources(path: String): String {
        this.javaClass.classLoader?.let {
            val stream = it.getResource(path)
            return String(stream.readBytes())
        } ?: run {
            return ""
        }
    }
}
