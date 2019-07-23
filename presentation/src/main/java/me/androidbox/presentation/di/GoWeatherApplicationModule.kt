package me.androidbox.presentation.di

import android.content.Context
import dagger.Module
import dagger.Provides
import me.androidbox.presentation.common.SchedulerProvider
import me.androidbox.presentation.common.SchedulerProviderImp
import javax.inject.Singleton

@Module
class GoWeatherApplicationModule(private val application: GoWeatherApplication) {

    @Provides
    @Singleton
    fun provideContext(): Context = application

    @Provides
    @Singleton
    fun provideScheduler(): SchedulerProvider = SchedulerProviderImp()
}
