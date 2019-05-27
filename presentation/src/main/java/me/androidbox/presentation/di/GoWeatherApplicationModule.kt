package me.androidbox.presentation.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.Reusable
import me.androidbox.presentation.common.SchedulerProvider
import me.androidbox.presentation.common.SchedulerProviderImp

@Module
object GoWeatherApplicationModule {

    @Provides
    @Reusable
    @JvmStatic
    fun provideContext(goWeatherApplication: GoWeatherApplication): Context =
            goWeatherApplication.applicationContext

    @Provides
    @Reusable
    @JvmStatic
    fun provideScheduler(): SchedulerProvider = SchedulerProviderImp()
}
