package me.androidbox.dicore

import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class])
interface GoWeatherComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: GoWeatherApplication): Builder

        fun build(): GoWeatherComponent
    }

    fun inject(application: GoWeatherApplication)
}
