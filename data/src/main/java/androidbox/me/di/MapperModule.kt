package androidbox.me.di

import androidbox.me.mappers.ForecastRequestDomainMapper
import androidbox.me.mappers.ForecastRequestDomainMapperImp
import androidbox.me.mappers.ForecastRequestEntityMapper
import androidbox.me.mappers.ForecastRequestEntityMapperImp
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class MapperModule {

    @Reusable
    @Provides
    fun provideForecastRequestDomainMapper(): ForecastRequestDomainMapper =
            ForecastRequestDomainMapperImp()

    @Reusable
    @Provides
    fun provideForecastRequestEntityMapper(): ForecastRequestEntityMapper =
            ForecastRequestEntityMapperImp()
}
