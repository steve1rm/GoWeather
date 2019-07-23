package me.androidbox.presentation.di

import android.app.Application

class GoWeatherApplication : Application() {
    lateinit var component: GoWeatherComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerGoWeatherComponent
            .builder()
            .goWeatherApplicationModule(GoWeatherApplicationModule(this@GoWeatherApplication))
            .build()

            component.inject(this@GoWeatherApplication)
    }
}
