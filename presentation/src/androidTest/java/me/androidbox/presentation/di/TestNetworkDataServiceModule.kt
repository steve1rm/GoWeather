package me.androidbox.presentation.di

import android.content.Context
import androidbox.me.mappers.ForecastRequestDomainMapper
import androidbox.me.mappers.ForecastRequestEntityMapper
import androidbox.me.network.ForecastRequestImp
import androidbox.me.network.WeatherForecastService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import me.androidbox.data.R
import me.androidbox.interactors.forecast.WeatherForecast
import retrofit2.Retrofit
import javax.inject.Named

@Module
class TestNetworkDataServiceModule {

    @Named("ApiKey")
    @Reusable
    @Provides
    fun provideApiKey(context: Context): String =
            context.getString(R.string.goweatherapikey)

    @Reusable
    @Provides
    fun provideWeatherForecastService(retrofit: Retrofit): WeatherForecastService =
        retrofit.create(WeatherForecastService::class.java)

    @Reusable
    @Provides
    fun provideForecastRequestImp(weatherForecastService: WeatherForecastService,
                                  @Named("ApiKey") apiKey: String,
                                  forecastRequestEntityMapper: ForecastRequestEntityMapper,
                                  forecastRequestDomainMapper: ForecastRequestDomainMapper): WeatherForecast =
        ForecastRequestImp(weatherForecastService, apiKey, forecastRequestEntityMapper, forecastRequestDomainMapper)
}
