package me.androidbox.dicore

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class GoWeatherApplicationModule {

    @Provides
    @Reusable
    fun provideContext(goWeatherApplication: GoWeatherApplication): Context =
            goWeatherApplication.applicationContext
}