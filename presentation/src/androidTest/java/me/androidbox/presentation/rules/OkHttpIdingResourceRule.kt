package me.androidbox.presentation.rules

import android.app.Application
import android.app.Instrumentation
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.platform.app.InstrumentationRegistry
import com.jakewharton.espresso.OkHttp3IdlingResource
import dagger.android.AndroidInjection
import me.androidbox.presentation.di.AndroidTestGoWeatherApplication
import me.androidbox.presentation.di.AndroidTestGoWeatherPresentationComponent
import okhttp3.OkHttpClient
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class OkHttpIdingResourceRule(application: Application) : TestRule {
    private val testApplication =
        InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
                as AndroidTestGoWeatherApplication

    // private val testApplication = application.applicationContext as AndroidTestGoWeatherApplication
    private val component = testApplication.component as AndroidTestGoWeatherPresentationComponent
    private val okHttpClient: OkHttpClient = component.okHttpClient()

    private val idlingResource: IdlingResource = OkHttp3IdlingResource.create("okhttp", okHttpClient)

    override fun apply(base: Statement?, description: Description?): Statement {
        return object: Statement() {
            override fun evaluate() {
                IdlingRegistry.getInstance().register(idlingResource)
                base?.evaluate()
                IdlingRegistry.getInstance().unregister(idlingResource)
            }
        }
    }
}
