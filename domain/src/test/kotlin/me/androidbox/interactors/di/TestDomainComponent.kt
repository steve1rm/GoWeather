package me.androidbox.interactors.di

import dagger.Component
import me.androidbox.interactors.WeatherForecastInteractorImpTest
import javax.inject.Singleton

@Singleton
@Component(modules = [TestInteractorDomainModule::class])
interface TestDomainComponent {
    fun inject(weatherForecastInteractorImpTest: WeatherForecastInteractorImpTest)
}

