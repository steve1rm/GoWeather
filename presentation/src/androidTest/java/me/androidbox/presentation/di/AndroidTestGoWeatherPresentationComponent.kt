package me.androidbox.presentation.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    TestNetworkModule::class,
    TestNetworkDataServiceModule::class,
    TestGoWeatherApplicationModule::class,
    TestMapperModule::class,
    TestSubComponentBuilder::class,
    TestForecastModule::class])
interface AndroidTestGoWeatherPresentationComponent : AndroidInjector<AndroidTestGoWeatherApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<AndroidTestGoWeatherApplication>() {
        abstract fun applicationModule(TestApplicationModule: TestGoWeatherApplicationModule): Builder

        abstract fun testNetworkModule(testNetworkModule: TestNetworkModule): Builder
    }
}
