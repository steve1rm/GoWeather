package me.androidbox.presentation.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class TestGoWeatherApplicationModule {
    @Provides
    @Reusable
    fun provideContext(goWeatherApplication: AndroidTestGoWeatherApplication): Context =
        goWeatherApplication.applicationContext
}