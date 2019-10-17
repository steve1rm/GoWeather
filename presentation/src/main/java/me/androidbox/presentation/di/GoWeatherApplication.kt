package me.androidbox.presentation.di

import android.app.Application
import androidbox.me.di.MapperModule
import me.androidbox.presentation.di.application.DaggerGoWeatherApplicationComponent
import me.androidbox.presentation.di.application.GoWeatherApplicationModule
import me.androidbox.presentation.di.application.GoWeatherApplicationComponent

class GoWeatherApplication : Application() {
    lateinit var goWeatherApplicationComponent: GoWeatherApplicationComponent

    override fun onCreate() {
        super.onCreate()

        goWeatherApplicationComponent = DaggerGoWeatherApplicationComponent
            .builder()
            .goWeatherApplicationModule(GoWeatherApplicationModule(this@GoWeatherApplication))
            .build()
    }
}
