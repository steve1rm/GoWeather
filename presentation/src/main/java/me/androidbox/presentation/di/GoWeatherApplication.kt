package me.androidbox.presentation.di

import android.app.Application
import androidbox.me.di.MapperModule
import com.jakewharton.threetenabp.AndroidThreeTen
import me.androidbox.presentation.di.application.DaggerGoWeatherApplicationComponent
import me.androidbox.presentation.di.application.GoWeatherApplicationModule
import me.androidbox.presentation.di.application.GoWeatherApplicationComponent
import timber.log.Timber

class GoWeatherApplication : Application() {
    lateinit var goWeatherApplicationComponent: GoWeatherApplicationComponent

    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)

        Timber.plant(Timber.DebugTree())

        goWeatherApplicationComponent = DaggerGoWeatherApplicationComponent
            .builder()
            .goWeatherApplicationModule(GoWeatherApplicationModule(this@GoWeatherApplication))
            .build()
    }
}
