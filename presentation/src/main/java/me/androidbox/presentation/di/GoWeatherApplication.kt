package me.androidbox.presentation.di

import android.app.Activity
import android.app.Application
import android.app.Service
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import dagger.android.support.HasSupportFragmentInjector
import me.androidbox.presentation.forecast.ForecastActivity
import javax.inject.Inject

open class GoWeatherApplication : Application(), HasActivityInjector, HasSupportFragmentInjector, HasServiceInjector {

    @Inject
    lateinit var dispatchingAndroidActivityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var dispatchingAndroidFragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var dispatchingAndroidServiceInjector: DispatchingAndroidInjector<Service>

    lateinit var component: GoWeatherComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerGoWeatherComponent
            .builder()
            .application(this)
            .build()

            component.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidActivityInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidFragmentInjector
    }

    override fun serviceInjector(): AndroidInjector<Service> {
        return dispatchingAndroidServiceInjector
    }
}
