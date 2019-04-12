package me.androidbox.presentation.di

import androidbox.me.di.MapperModule
import androidbox.me.di.NetworkDataServiceModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ActivityBuilder::class, ActivityModule::class, NetworkModule::class, NetworkDataServiceModule::class, MapperModule::class, GoWeatherApplicationModule::class])
interface GoWeatherComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: GoWeatherApplication): Builder

        fun build(): GoWeatherComponent
    }

    fun inject(application: GoWeatherApplication)
}
