package me.androidbox.presentation.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.Reusable
import me.androidbox.presentation.BuildConfig
import me.androidbox.presentation.R
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class NetworkModule {

    @Reusable
    @Provides
    fun httpLogginInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()

        loggingInterceptor.level = if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        }
        else {
            HttpLoggingInterceptor.Level.NONE
        }

        return loggingInterceptor
    }

    @Reusable
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Named("BaseUrl")
    @Reusable
    @Provides
    fun provideBaseUrl(context: Context): String =
        context.getString(R.string.baseUrl)


    @Named("TestBaseUrl")
    @Reusable
    @Provides
    fun provideBaseUrlTest(): String =
        "http://localhost:8080/"

    @Reusable
    @Provides
    fun provideRetrofit(@Named("BaseUrl") baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}

/* http://localhost:57198/ */
