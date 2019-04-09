package androidbox.me.di

import android.content.Context
import androidbox.me.mappers.ForecastRequestDomainMapper
import androidbox.me.mappers.ForecastRequestEntityMapper
import androidbox.me.network.ForecastRequest
import androidbox.me.network.ForecastRequestImp
import androidbox.me.network.WeatherForecastService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import me.androidbox.data.R
import retrofit2.Retrofit

@Module
class NetworkDataServiceModule {

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
                                  apiKey: String,
                                  forecastRequestEntityMapper: ForecastRequestEntityMapper,
                                  forecastRequestDomainMapper: ForecastRequestDomainMapper): ForecastRequest =
        ForecastRequestImp(weatherForecastService, apiKey, forecastRequestEntityMapper, forecastRequestDomainMapper)
}
