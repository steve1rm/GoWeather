package androidbox.me.network.di

import androidbox.me.di.MapperModule
import androidbox.me.network.ForecastRequestImpTest
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MapperModule::class])
interface TestDataComponent {
    fun inject(forecastRequestImpTest: ForecastRequestImpTest)
}