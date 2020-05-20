package me.androidbox.presentation.di.application

import android.app.Application
import android.content.Context
import androidbox.me.local.DatabaseService
import androidbox.me.local.migrations.MIGRATION_1_2
import androidx.room.Room
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import me.androidbox.presentation.common.SchedulerProvider
import me.androidbox.presentation.common.SchedulerProviderImp
import javax.inject.Singleton

@Module
class GoWeatherApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Application = application


    @Provides
    @Singleton
    fun provideContext(): Context = application

    @Provides
    @Singleton
    fun provideScheduler(): SchedulerProvider = SchedulerProviderImp()

    @Provides
    @Singleton
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    @Singleton
    fun provideDatabaseService(): DatabaseService {
        return Room.databaseBuilder(
            application,
            DatabaseService::class.java,
            "goweather-database.db"
        ).build()
    }
}

