package me.androidbox.presentation.di.application

import android.content.Context
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import me.androidbox.presentation.common.SchedulerProvider
import me.androidbox.presentation.common.SchedulerProviderImp
import me.androidbox.presentation.di.GoWeatherApplication
import javax.inject.Singleton

@Module
class GoWeatherApplicationModule(private val application: GoWeatherApplication) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application

    @Provides
    @Singleton
    fun provideScheduler(): SchedulerProvider = SchedulerProviderImp()

    @Provides
    @Singleton
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()
}

