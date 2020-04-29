package me.androidbox.presentation.forecast.automation

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import me.androidbox.presentation.forecast.ForecastActivity
import me.androidbox.presentation.pages.ForecastHeaderPage
import me.androidbox.presentation.pages.ForecastListPage
import me.androidbox.presentation.rules.TestComponentRule
import me.androidbox.presentation.screens.ForecastHeaderScreen
import me.androidbox.presentation.utils.EspressoIdlingResource
import me.androidbox.presentation.utils.appendDegreesSymbol
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ForecastTest {

    private val mockWebServer: MockWebServer by lazy {
        MockWebServer()
    }
    private val goWeatherComponent = TestComponentRule(InstrumentationRegistry.getInstrumentation().targetContext)
    private val forecast = IntentsTestRule(ForecastActivity::class.java, false, false)

    @get:Rule
    val chain: RuleChain = RuleChain.outerRule(goWeatherComponent).around(forecast)

    @Before
    fun setup() {
        mockWebServer.start(8080)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun shouldShowForecastHeader() {
        mockWebServer.enqueue(MockResponse().setBody(loadFromResources("json/fivedayforecast.json")))
        mockWebServer.enqueue(MockResponse().setBody(loadFromResources("json/currentWeather.json")))

        ActivityScenario.launch(ForecastActivity::class.java)

        ForecastHeaderPage
            .shouldBeVisible()
            .shouldDisplayCurrentTemperature(32.8F.appendDegreesSymbol)
            .shouldDisplayFeelsLikeTemperature("Feels like 35.6")
            .shouldDisplayLocation("Watthana")

        ForecastListPage
            .shouldBeVisible()
            .shouldHaveSize(17)
            .shouldWeatherItemAtFirstPosition(
                "Tuesday, Dec 31",
                "Broken clouds",
                34.6F.appendDegreesSymbol,
                27.2F.appendDegreesSymbol)
            .shouldWeatherItemAtPosition(1,
                "Wednesday, Jan 01",
                "Scattered rain",
                34.5F.appendDegreesSymbol,
                27.4F.appendDegreesSymbol)
            .shouldWeatherItemAtLastPosition(
                "Thursday, Jan 16",
                "Clear sky",
                26.7F.appendDegreesSymbol,
                23.4F.appendDegreesSymbol)


    }

    /* TODO make this common */
    private fun loadFromResources(path: String): String {
        this.javaClass.classLoader?.let {
            val stream = it.getResource(path)
            return String(stream.readBytes())
        } ?: run {
            return ""
        }
    }

}
