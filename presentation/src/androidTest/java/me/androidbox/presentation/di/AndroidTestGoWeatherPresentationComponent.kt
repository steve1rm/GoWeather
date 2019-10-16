package me.androidbox.presentation.di

import androidx.test.espresso.IdlingResource
import dagger.Component
import me.androidbox.presentation.di.application.GoWeatherApplicationComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Singleton
@Component(modules = [
    TestNetworkModule::class,
    TestNetworkDataServiceModule::class,
    TestGoWeatherApplicationModule::class,
    TestMapperModule::class,
    TestForecastModule::class,
    TestActivityModule::class])
interface AndroidTestGoWeatherPresentationComponent :
    GoWeatherApplicationComponent {

  /*  fun schedulerProvider(): SchedulerProvider */
    fun okHttpClient(): OkHttpClient
    fun idlingResource(): IdlingResource
}
