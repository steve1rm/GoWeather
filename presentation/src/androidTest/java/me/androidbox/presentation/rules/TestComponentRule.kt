package me.androidbox.presentation.rules

import android.content.Context
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import me.androidbox.presentation.di.*
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class TestComponentRule(private val context: Context): TestRule {

    private lateinit var androidTestGoWeatherPresentationComponent: AndroidTestGoWeatherPresentationComponent
    fun getContext() = context

    private lateinit var idlingResource: IdlingResource

    private fun setupDaggerTestComponentInApplication() {
        val application = context.applicationContext as GoWeatherApplication

        androidTestGoWeatherPresentationComponent =
            DaggerAndroidTestGoWeatherPresentationComponent.builder()
                .testGoWeatherApplicationModule(TestGoWeatherApplicationModule(application))
                .testNetworkModule(TestNetworkModule())
                .testActivityModule(TestActivityModule())
                .build()

        application.goWeatherApplicationComponent = androidTestGoWeatherPresentationComponent

        idlingResource = androidTestGoWeatherPresentationComponent.idlingResource()
    }

    override fun apply(base: Statement, description: Description): Statement {
        return object: Statement() {
            override fun evaluate() {
                setupDaggerTestComponentInApplication()
                IdlingRegistry.getInstance().register(idlingResource)
           //     Espresso.registerIdlingResources(idlingResource)
                base.evaluate()
             //   Espresso.unregisterIdlingResources(idlingResource)
                 IdlingRegistry.getInstance().unregister(idlingResource)
            }
        }
    }
}
