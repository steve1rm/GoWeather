package me.androidbox.presentation.testrunner

import android.os.Bundle
import androidx.test.runner.AndroidJUnitRunner
import com.facebook.testing.screenshot.ScreenshotRunner

class GoWeatherTestRunner : AndroidJUnitRunner() {

    override fun onCreate(arguments: Bundle?) {
        super.onCreate(arguments)
        ScreenshotRunner.onCreate(this, arguments)
    }

    override fun finish(resultCode: Int, results: Bundle?) {
        ScreenshotRunner.onDestroy()
        super.finish(resultCode, results)
    }
}