package me.androidbox.presentation.common

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulerProviderImp : SchedulerProvider {
    override fun backgroundScheduler() = Schedulers.io()

    override fun uiScheduler() = AndroidSchedulers.mainThread()
}