package me.androidbox.presentation.forecast.screentests

import android.content.Context
import android.view.LayoutInflater
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.facebook.testing.screenshot.Screenshot
import com.facebook.testing.screenshot.ViewHelpers
import me.androidbox.presentation.R
import me.androidbox.presentation.forecast.ForecastActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ForecastListTest {

    @get:Rule
    val activityRule = ActivityTestRule(ForecastActivity::class.java, false, false)

    @Test
    fun `shouldShowForecastList`() {
        // Arrange
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val forecastListLayout = LayoutInflater.from(context).inflate(R.layout.weather_forecast, null, false)
        val forecastActivity = activityRule.activity

        ViewHelpers
            .setupView(forecastListLayout)
            .setExactWidthPx(1080)
            .setExactHeightPx(1920)
            .layout()

        // Act
        Screenshot
            .snapActivity(forecastActivity)
            .record()
    }
}