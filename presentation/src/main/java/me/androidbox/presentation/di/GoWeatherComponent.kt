package me.androidbox.presentation.di

import androidbox.me.di.MapperModule
import androidbox.me.di.NetworkDataServiceModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import me.androidbox.presentation.forecast.ForecastActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuilder::class,
    NetworkModule::class,
    NetworkDataServiceModule::class,
    MapperModule::class,
    GoWeatherApplicationModule::class])
interface GoWeatherComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: GoWeatherApplication): Builder

        fun build(): GoWeatherComponent
    }

    fun inject(application: GoWeatherApplication)
}
