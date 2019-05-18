package me.androidbox.presentation.di

import androidbox.me.mappers.ForecastRequestDomainMapper
import androidbox.me.mappers.ForecastRequestDomainMapperImp
import androidbox.me.mappers.ForecastRequestEntityMapper
import androidbox.me.mappers.ForecastRequestEntityMapperImp
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
}
