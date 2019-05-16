package me.androidbox.presentation.di


import com.nhaarman.mockitokotlin2.mock
import dagger.Module
import dagger.Provides
import dagger.Reusable
import me.androidbox.presentation.common.LocationUtils

@Module
class ActivityTestModule {

    @Reusable
    @Provides
    fun provideLocationUtils(): LocationUtils = mock()
}