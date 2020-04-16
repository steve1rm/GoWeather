package me.androidbox.presentation.forecast

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.jakewharton.espresso.OkHttp3IdlingResource
import me.androidbox.presentation.R
import me.androidbox.presentation.rules.MockWebServerRule
import me.androidbox.presentation.rules.OkHttpIdingResourceRule
import me.androidbox.presentation.rules.TestComponentRule
import me.androidbox.presentation.utils.EspressoIdlingResource
import me.androidbox.presentation.utils.appendDegreesSymbol
import me.androidbox.presentation.viewAssertions.childAtPosition
import okhttp3.HttpUrl
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.startsWith
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class ForecastActivityAndroidTest {

    @Inject
    lateinit var okHttpClient: OkHttpClient

    @get:Rule
    val activityRule = ActivityTestRule(ForecastActivity::class.java, false, false)

    private val mockWebserver: MockWebServer by lazy {
        MockWebServer()
    }

    private val goWeatherComponent = TestComponentRule(InstrumentationRegistry.getInstrumentation().targetContext)

    private val forecast = IntentsTestRule(ForecastActivity::class.java, false, false)

    @get:Rule
    val chain: RuleChain = RuleChain.outerRule(goWeatherComponent).around(forecast)


    @Before
    fun setUp() {
        mockWebserver.start(8080)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun tearDown() {
        mockWebserver.shutdown()
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun should_load_five_day_forecast() {
        mockWebserver.enqueue(MockResponse().setBody(loadFromResources("json/fivedayforecast.json")))
        mockWebserver.enqueue(MockResponse().setBody(loadFromResources("json/currentWeather.json")))

        ActivityScenario.launch(ForecastActivity::class.java)

        /* should display Title of app in the toolbar */
        onView((withId(R.id.action_bar)))
            .check(matches(allOf(hasDescendant(withText(R.string.app_name)), isDisplayed())))

        /* should display the current temperature */
        onView(withId(R.id.tvTemperatureDegrees))
            .check(matches(allOf(withText(32.8F.appendDegreesSymbol), isDisplayed())))

        /* should display the feels like temperature */
        onView(withId(R.id.tvFeelsLikeTemperatureDegrees))
            .check(matches(allOf(withText(startsWith("Feels like 35.6")), isDisplayed())))

        /* should display the current location */
        onView(withId(R.id.tvLocationName))
            .check(matches(allOf(withText("Watthana"), isDisplayed())))

        /* should display the daily forecast */
        onView(withId(R.id.rvDailyForecast)).check(matches(withEffectiveVisibility(VISIBLE)))

        /* should display the number of forecast days */
       // onView(withId(R.id.rvDailyForecast)).check(matches(hasChildCount(4)))

        /* Should display the correct day and average temperature at position 0 */
        onView(childAtPosition(withId(R.id.rvDailyForecast), 0))
            .check(matches(hasDescendant(allOf(withId(R.id.tvWeekDay), withText("Tuesday, Dec 31"), withEffectiveVisibility(VISIBLE)))))
        onView(childAtPosition(withId(R.id.rvDailyForecast), 0))
            .check(matches(hasDescendant(allOf(withId(R.id.tvHighTemperature), withText(34.6F.appendDegreesSymbol), withEffectiveVisibility(VISIBLE)))))

        /* Should display the correct day and average temperature at position 1 */
        onView(childAtPosition(withId(R.id.rvDailyForecast), 1))
            .check(matches(hasDescendant(allOf(withId(R.id.tvWeekDay), withText("Wednesday, Jan 01"), withEffectiveVisibility(VISIBLE)))))
        onView(childAtPosition(withId(R.id.rvDailyForecast), 1))
            .check(matches(hasDescendant(allOf(withId(R.id.tvHighTemperature), withText(34.5F.appendDegreesSymbol), withEffectiveVisibility(VISIBLE)))))

        /* Should display the correct day and average temperature at position 2 */
        onView(childAtPosition(withId(R.id.rvDailyForecast), 2))
            .check(matches(hasDescendant(allOf(withId(R.id.tvWeekDay), withText("Thursday, Jan 02"), withEffectiveVisibility(VISIBLE)))))
        onView(childAtPosition(withId(R.id.rvDailyForecast), 2))
            .check(matches(hasDescendant(allOf(withId(R.id.tvHighTemperature), withText(34.8F.appendDegreesSymbol), withEffectiveVisibility(VISIBLE)))))

        /* Should display the correct day and average temperature at position 3 */
        onView(childAtPosition(withId(R.id.rvDailyForecast), 3))
            .check(matches(hasDescendant(allOf(withId(R.id.tvWeekDay), withText("Friday, Jan 03"), withEffectiveVisibility(VISIBLE)))))
        onView(childAtPosition(withId(R.id.rvDailyForecast), 3))
            .check(matches(hasDescendant(allOf(withId(R.id.tvHighTemperature), withText(35.1F.appendDegreesSymbol), withEffectiveVisibility(VISIBLE)))))
    }

    @Test
    fun return_404_error_response() {
        mockWebserver.enqueue(MockResponse().setResponseCode(404))

        forecast.launchActivity(Intent(goWeatherComponent.getContext(), ForecastActivity::class.java))

        /* should display Title of app in the toolbar */
        onView((withId(R.id.action_bar)))
            .check(matches(allOf(hasDescendant(withText(R.string.app_name)), isDisplayed())))

        /* should display the failure message */
        onView(withId(R.id.tvFailureMessage))
            .check(matches(allOf(withText(R.string.failure_message), isDisplayed())))

        /* should display a retry button */
        onView(withId(R.id.btnRetry)).check(matches(isDisplayed()))

        /* should try and get weather data when clicking on the retry button */
        onView(withId(R.id.btnRetry)).perform(click())

        /* Should display loading */
        onView(withId(R.id.ivProgress)).check(matches(isDisplayed()))
    }

    @Test
    fun return_malformed_json_response() {
        mockWebserver.enqueue(MockResponse().setBody("malformed json response"))

        ActivityScenario.launch(ForecastActivity::class.java)

        onView(withText(R.string.app_name))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.action_bar), hasDescendant(withText("GoWeather"))))
            .check(matches(isDisplayed()))
    }

    @Test
    fun displayRetryFragment_when_timeoutOccurs() {
        loadFromResources("json/fivedayforecast.json")
        mockWebserver.enqueue(MockResponse()
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
}
