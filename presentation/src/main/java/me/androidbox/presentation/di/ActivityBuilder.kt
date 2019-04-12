package me.androidbox.presentation.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.androidbox.presentation.HomeActivity

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [ActivityModule::class])
    abstract fun injectIntoHomeActivity(): HomeActivity
}