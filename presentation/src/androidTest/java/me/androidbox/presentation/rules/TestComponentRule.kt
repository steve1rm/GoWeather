package me.androidbox.presentation.rules

import android.content.Context
import me.androidbox.presentation.di.*
import me.androidbox.presentation.di.application.DaggerGoWeatherApplicationComponent
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class TestComponentRule(private val context: Context): TestRule {

    lateinit var androidTestGoWeatherPresentationComponent: AndroidTestGoWeatherPresentationComponent
    fun getContext() = context

    private fun setupDaggerTestComponentInApplication() {
        val application = context.applicationContext as GoWeatherApplication

        androidTestGoWeatherPresentationComponent =
            DaggerAndroidTestGoWeatherPresentationComponent.builder()
                .testGoWeatherApplicationModule(TestGoWeatherApplicationModule(application))
                .testNetworkModule(TestNetworkModule())
                .testActivityModule(TestActivityModule())
                .build()

        application.goWeatherApplicationComponent = androidTestGoWeatherPresentationComponent


    }

    override fun apply(base: Statement, description: Description): Statement {
        return object: Statement() {
            override fun evaluate() {
                setupDaggerTestComponentInApplication()
                base.evaluate()
            }
        }
    }
}
