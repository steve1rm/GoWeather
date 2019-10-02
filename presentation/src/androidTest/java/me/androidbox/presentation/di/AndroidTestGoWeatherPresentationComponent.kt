package me.androidbox.presentation.di

import androidx.test.espresso.IdlingResource
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.reactivex.disposables.CompositeDisposable
import me.androidbox.presentation.common.SchedulerProvider
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
interface AndroidTestGoWeatherPresentationComponent : GoWeatherComponent {

  /*  fun schedulerProvider(): SchedulerProvider */
    fun okHttpClient(): OkHttpClient
    fun idlingResource(): IdlingResource
}
