package me.androidbox.presentation.di

import androidbox.me.mappers.*
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class TestMapperModule {

    @Reusable
    @Provides
    fun provideForecastRequestDomainMapper(): ForecastRequestDomainMapper =
            ForecastRequestDomainMapperImp()

    @Reusable
    @Provides
    fun provideForecastRequestEntityMapper(): ForecastRequestEntityMapper =
            ForecastRequestEntityMapperImp()

    @Reusable
    @Provides
    fun provideCurrentRequestEntityMapper(): CurrentRequestEntityMapper =
        CurrentRequestEntityMapperImp()

    @Reusable
    @Provides
    fun provideCurrentResponseDomainMapper(): CurrentResponseDomainMapper =
        CurrentResponseDomainMapperImp()
}
