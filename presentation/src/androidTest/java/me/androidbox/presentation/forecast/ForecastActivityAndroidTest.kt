package me.androidbox.presentation.forecast

import android.app.Activity
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.DispatchingAndroidInjector_Factory
import me.androidbox.presentation.R
import me.androidbox.presentation.common.LocationUtils
import me.androidbox.presentation.di.DaggerAndroidTestGoWeatherPresentationComponent
import me.androidbox.presentation.di.TestGoWeatherApplicationModule
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
import org.junit.runner.RunWith
import java.lang.Thread.sleep
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

    @Inject
    lateinit var okHttpClient: OkHttpClient

   /* @Inject
    @get:Rule
    lateinit var okHttpIdingResourceRule: OkHttpIdingResourceRule
*/

    @get:Rule
    val activityRule = ActivityTestRule(ForecastActivity::class.java, false, false)

    private val mockWebserver: MockWebServer by lazy {
        MockWebServer()
    }

    @Before
    fun setUp() {
     /*   val testApplication =
            InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
                    as AndroidTestGoWeatherApplication
*/
        DaggerAndroidTestGoWeatherPresentationComponent
            .builder()

          /*  .applicationModule(TestGoWeatherApplicationModule())
            .create(testApplication)
            .inject(testApplication)*/

        mockWebserver.start(8080)
    }

    @After
    fun tearDown() {
        mockWebserver.shutdown()
    }

    @Test
    fun should_load_five_day_forecast() {
        loadFromResources("json/fivedayforecast.json")
        mockWebserver.enqueue(MockResponse().setBody(loadFromResources("json/fivedayforecast.json")))

        ActivityScenario.launch(ForecastActivity::class.java)

        /* should display Title of app in the toolbar */
        onView((withId(R.id.action_bar)))
            .check(matches(allOf(hasDescendant(withText(R.string.app_name)), isDisplayed())))

        /* should display the initial loading screen */
        onView(withId(R.id.ivProgress)).check(matches(isDisplayed()))

        /* should display the current temperature */
        onView(withId(R.id.tvTemperatureDegrees))
            .check(matches(allOf(withText(startsWith("36")), isDisplayed())))

        /* should display the current location */
        onView(withId(R.id.tvLocationName))
            .check(matches(allOf(withText("Bangkok"), isDisplayed())))

        /* should display the daily forecast */
        onView(withId(R.id.rvDailyForecast)).check(matches(isDisplayed()))

        /* should display the number of forecast days */
        onView(withId(R.id.rvDailyForecast)).check(matches(hasChildCount(4)))

        /* Should display the correct day and average temperature at position 0 */
        onView(childAtPosition(withId(R.id.rvDailyForecast), 0))
            .check(matches(hasDescendant(allOf(withId(R.id.tvWeekDay), withText("Sunday"), withEffectiveVisibility(VISIBLE)))))
        onView(childAtPosition(withId(R.id.rvDailyForecast), 0))
            .check(matches(hasDescendant(allOf(withId(R.id.tvAverageTemperature), withText("33.6"), withEffectiveVisibility(VISIBLE)))))

        /* Should display the correct day and average temperature at position 1 */
        onView(childAtPosition(withId(R.id.rvDailyForecast), 1))
            .check(matches(hasDescendant(allOf(withId(R.id.tvWeekDay), withText("Monday"), withEffectiveVisibility(VISIBLE)))))
        onView(childAtPosition(withId(R.id.rvDailyForecast), 1))
            .check(matches(hasDescendant(allOf(withId(R.id.tvAverageTemperature), withText("33.4"), withEffectiveVisibility(VISIBLE)))))

        /* Should display the correct day and average temperature at position 2 */
        onView(childAtPosition(withId(R.id.rvDailyForecast), 2))
            .check(matches(hasDescendant(allOf(withId(R.id.tvWeekDay), withText("Tuesday"), withEffectiveVisibility(VISIBLE)))))
        onView(childAtPosition(withId(R.id.rvDailyForecast), 2))
            .check(matches(hasDescendant(allOf(withId(R.id.tvAverageTemperature), withText("34.1"), withEffectiveVisibility(VISIBLE)))))

        /* Should display the correct day and average temperature at position 3 */
        onView(childAtPosition(withId(R.id.rvDailyForecast), 3))
            .check(matches(hasDescendant(allOf(withId(R.id.tvWeekDay), withText("Wednesday"), withEffectiveVisibility(VISIBLE)))))
        onView(childAtPosition(withId(R.id.rvDailyForecast), 3))
            .check(matches(hasDescendant(allOf(withId(R.id.tvAverageTemperature), withText("34.4"), withEffectiveVisibility(VISIBLE)))))
    }

    @Test
    fun return_404_error_response() {
        mockWebserver.enqueue(MockResponse().setResponseCode(404))

        ActivityScenario.launch(ForecastActivity::class.java)

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

        sleep(6000L)

        /* should go back to the failure screen */
        onView(withId(R.id.tvFailureMessage))
            .check(matches(allOf(withText(R.string.failure_message), isDisplayed())))

        /* should display a retry button */
        onView(withId(R.id.btnRetry))
            .check(matches(allOf(withText(R.string.retry), isDisplayed())))
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

    private fun createFakeMainActivityInjector(block : ForecastActivity.() -> Unit)
            : DispatchingAndroidInjector<Activity> {
        val injector = AndroidInjector<Activity> { instance ->
            if (instance is ForecastActivity) {
                instance.block()
            }
        }
        val factory = AndroidInjector.Factory<Activity> { injector }
        val map = mapOf(Pair<Class <*>,
                Provider<AndroidInjector.Factory<*>>>(ForecastActivity::class.java, Provider { factory } ))

        val stringMap : Map<String, Provider<AndroidInjector.Factory<*>>> = emptyMap()

        return DispatchingAndroidInjector_Factory.newDispatchingAndroidInjector(map, stringMap)
    }
}
