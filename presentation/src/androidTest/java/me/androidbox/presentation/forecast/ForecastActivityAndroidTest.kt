package me.androidbox.presentation.forecast

import android.app.Activity
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.DispatchingAndroidInjector_Factory
import me.androidbox.presentation.R
import me.androidbox.presentation.common.LocationUtils
import me.androidbox.presentation.di.AndroidTestGoWeatherApplication
import me.androidbox.presentation.di.DaggerAndroidTestGoWeatherPresentationComponent
import me.androidbox.presentation.di.TestGoWeatherApplicationModule
import me.androidbox.presentation.rules.MockWebServerRule
import okhttp3.HttpUrl
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.mockwebserver.MockResponse
import org.hamcrest.CoreMatchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Provider

@RunWith(AndroidJUnit4::class)
class ForecastActivityAndroidTest {

    @field:[Inject Named("TestBaseUrl")]
    lateinit var baseUrl: String

    @Inject
    lateinit var locationUtils: LocationUtils

    @Inject
    lateinit var presenter: ForecastPresenter

    @get:Rule
    val activityRule = ActivityTestRule(ForecastActivity::class.java, false, false)

    @get:Rule
    val mockWebServerRule = MockWebServerRule()

    @Before
    fun setUp() {
        val testApplication =
            InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
                    as AndroidTestGoWeatherApplication

        DaggerAndroidTestGoWeatherPresentationComponent
            .builder()
            .applicationModule(TestGoWeatherApplicationModule())
            .create(testApplication)
            .inject(testApplication)
    }

    @Test
    fun appLaunchedSuccessfully() {
        loadFromResources("json/fivedayforecast.json")
        mockWebServerRule.mockWebServer.enqueue(MockResponse().setBody(loadFromResources("json/fivedayforecast.json")))

        ActivityScenario.launch(ForecastActivity::class.java)

        onView(withText(R.string.app_name))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.action_bar), hasDescendant(withText("GoWeather"))))
            .check(matches(isDisplayed()))
    }

    @Test
    fun `return_404_error_response`() {
        mockWebServerRule.mockWebServer.enqueue(MockResponse().setResponseCode(404))

        ActivityScenario.launch(ForecastActivity::class.java)

        onView(withText(R.string.app_name))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.action_bar), hasDescendant(withText("GoWeather"))))
            .check(matches(isDisplayed()))
    }

    @Test
    fun return_malformed_json_response() {
        mockWebServerRule.mockWebServer.enqueue(MockResponse().setBody("malformed json response"))

        ActivityScenario.launch(ForecastActivity::class.java)

        onView(withText(R.string.app_name))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.action_bar), hasDescendant(withText("GoWeather"))))
            .check(matches(isDisplayed()))
    }

    @Test
    fun displayRetryFragment_when_timeoutOccurs() {
        loadFromResources("json/fivedayforecast.json")
        mockWebServerRule.mockWebServer.enqueue(MockResponse()
            .setBody(loadFromResources("json/fivedayforecast.json"))
            .throttleBody(1, 5, TimeUnit.SECONDS))

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

    private fun createFakeMainActivityInjector(block : ForecastActivity.() -> Unit)
            : DispatchingAndroidInjector<Activity> {
        val injector = AndroidInjector<Activity> { instance ->
            if (instance is ForecastActivity) {
                instance.block()
            }
        }
        val factory = AndroidInjector.Factory<Activity> { injector }
        val map = mapOf(Pair<Class <*>,
                Provider<AndroidInjector.Factory<*>>>(ForecastActivity::class.java, Provider { factory }))

        val stringMap : Map<String, Provider<AndroidInjector.Factory<*>>> = emptyMap()

        return DispatchingAndroidInjector_Factory.newDispatchingAndroidInjector(map, stringMap)
    }
}
