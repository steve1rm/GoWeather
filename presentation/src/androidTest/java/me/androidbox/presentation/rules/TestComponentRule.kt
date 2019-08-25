package me.androidbox.presentation.rules

import android.content.Context
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import com.jakewharton.espresso.OkHttp3IdlingResource
import me.androidbox.presentation.di.*
import okhttp3.OkHttpClient
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class TestComponentRule(private val context: Context): TestRule {

    private lateinit var androidTestGoWeatherPresentationComponent: AndroidTestGoWeatherPresentationComponent
    private val idlingResource: IdlingResource by lazy {
        androidTestGoWeatherPresentationComponent.idlingResource()
    }
    fun getContext() = context



    private fun setupDaggerTestComponnentInApplication() {
        val application = context.applicationContext as GoWeatherApplication
        androidTestGoWeatherPresentationComponent =
            DaggerAndroidTestGoWeatherPresentationComponent.builder()
                .testGoWeatherApplicationModule(TestGoWeatherApplicationModule(application))
                .testNetworkModule(TestNetworkModule())
                .build()

        application.component = androidTestGoWeatherPresentationComponent
    }

    override fun apply(base: Statement, description: Description): Statement {
        return object: Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
        //        try {
                    setupDaggerTestComponnentInApplication()
                IdlingRegistry.getInstance().register(idlingResource)
                    base.evaluate()
                IdlingRegistry.getInstance().unregister(idlingResource)
   //             }
            /*    finally {
                    androidTestGoWeatherPresentationComponent = null
                }*/
            }
        }
    }
}