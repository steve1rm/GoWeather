package me.androidbox.presentation.forecast

import android.view.LayoutInflater
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
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val loadingView = LayoutInflater.from(context).inflate(R.layout.inprogress, null, false)

        ViewHelpers
            .setupView(loadingView)
            .setExactWidthDp(360)
            .layout()

        // Act & Assert
        Screenshot.snap(loadingView).record()
    }
}