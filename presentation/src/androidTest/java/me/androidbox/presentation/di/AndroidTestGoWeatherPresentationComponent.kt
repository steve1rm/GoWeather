package me.androidbox.presentation.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    TestNetworkModule::class,
    TestActivityModule::class,
    TestNetworkDataServiceModule::class,
    TestMapperModule::class,
    TestGoWeatherApplicationModule::class,
    TestActivityBuilder::class])
interface AndroidTestGoWeatherPresentationComponent : AndroidInjector<AndroidTestGoWeatherApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<AndroidTestGoWeatherApplication>() {
        abstract fun applicationModule(applicationModule: TestGoWeatherApplicationModule): Builder
    }
}

/*
TestActivityModule::class,
TestNetworkDataServiceModule::class,
TestMapperModule::class,
TestGoWeatherApplicationModule::class]*/
