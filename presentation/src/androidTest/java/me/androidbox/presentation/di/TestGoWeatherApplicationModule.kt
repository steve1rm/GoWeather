package me.androidbox.presentation.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TestGoWeatherApplicationModule(private val goWeatherApplication: GoWeatherApplication) {

    @Singleton
    @Provides
    fun providesContext(): Context {
        return goWeatherApplication
    }
}
