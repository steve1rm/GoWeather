package me.androidbox.presentation.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.Reusable
import me.androidbox.presentation.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
class TestGoWeatherApplicationModule {
  /*  @Provides
    @Reusable
    fun provideContext(goWeatherApplication: AndroidTestGoWeatherApplication): Context =
        goWeatherApplication.applicationContext*/
}
