package me.androidbox.presentation.common

import io.reactivex.schedulers.Schedulers

class TestSchedulerProviderImp : SchedulerProvider {
    override fun backgroundScheduler() = Schedulers.trampoline()

    override fun uiScheduler() = Schedulers.trampoline()
}