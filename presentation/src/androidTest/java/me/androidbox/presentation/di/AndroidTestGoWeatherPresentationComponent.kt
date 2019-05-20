package me.androidbox.presentation.di

import androidbox.me.di.MapperModule
import androidbox.me.di.NetworkDataServiceModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    TestNetworkModule::class,
    NetworkDataServiceModule::class,
    MapperModule::class,
    TestGoWeatherApplicationModule::class,
    TestSubComponentBuilder::class])
interface AndroidTestGoWeatherPresentationComponent : AndroidInjector<AndroidTestGoWeatherApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<AndroidTestGoWeatherApplication>() {
        abstract fun applicationModule(applicationModule: TestGoWeatherApplicationModule): Builder
    }
}
