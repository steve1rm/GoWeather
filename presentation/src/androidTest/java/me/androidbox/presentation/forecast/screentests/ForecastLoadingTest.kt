package me.androidbox.presentation.forecast.screentests

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.facebook.testing.screenshot.Screenshot
import com.facebook.testing.screenshot.ViewHelpers
import me.androidbox.presentation.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ForecastLoadingTest {

    @Test
    fun shouldShowLoadingScreen() {
        // Arrange
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val loadingView: View = LayoutInflater.from(context).inflate(R.layout.inprogress, null, false)

        ViewHelpers
            .setupView(loadingView)
            .setExactWidthPx(1080)
            .setExactHeightPx(1920)
            .layout()

        // Snap
        Screenshot
            .snap(loadingView)
            .record()
    }


    @Test
    fun shouldShowRetryScreen() {
        // Arrange
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val retryView: View = LayoutInflater.from(context).inflate(R.layout.failurecase_layout, null, false)

        ViewHelpers
            .setupView(retryView)
            .setExactWidthPx(1080)
            .setExactHeightPx(1920)
            .layout()

        // Snap
        Screenshot
            .snap(retryView)
            .record()

    }
}