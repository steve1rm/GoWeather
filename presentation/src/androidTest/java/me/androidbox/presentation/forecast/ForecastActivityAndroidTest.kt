package me.androidbox.presentation.forecast

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import me.androidbox.presentation.R
import me.androidbox.presentation.di.DaggerAndroidTestGoWeatherPresentationComponent
import me.androidbox.presentation.di.TestNetworkModule
import org.hamcrest.CoreMatchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Named

@RunWith(AndroidJUnit4::class)
class ForecastActivityAndroidTest {

    @field:[Inject Named("BaseUrl")]
    lateinit var baseUrl: String

    @get:Rule
    val activityRule = ActivityTestRule(ForecastActivity::class.java, false, false)

    @Before
    fun setUp() {
        DaggerAndroidTestGoWeatherPresentationComponent
            .builder()
            .build()
            .inject(this)
    }

    @Test
    fun appLaunchedSuccessfully() {
        ActivityScenario.launch(ForecastActivity::class.java)

        onView(withText(R.string.app_name))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.action_bar), hasDescendant(withText("GoWeather"))))
            .check(matches(isDisplayed()))

    }
}
