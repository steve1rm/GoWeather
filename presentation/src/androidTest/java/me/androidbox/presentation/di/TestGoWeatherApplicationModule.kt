package me.androidbox.presentation.di

import android.app.Application
import android.content.Context
import androidbox.me.local.DatabaseService
import androidx.room.Room
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class TestGoWeatherApplicationModule(private val goWeatherApplication: GoWeatherApplication) {

    @Singleton
    @Provides
    fun providesApplication(): Application {
        return goWeatherApplication
    }

    @Singleton
    @Provides
    fun providesContext(): Context {
        return goWeatherApplication
    }

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    @Singleton
    fun provideDatabaseService(application: Application): DatabaseService {
        return Room.databaseBuilder(
            application,
            DatabaseService::class.java,
            "goweather-database.db"
        ).build()
    }
}
