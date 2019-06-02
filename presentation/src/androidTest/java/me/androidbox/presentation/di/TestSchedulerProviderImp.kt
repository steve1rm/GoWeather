package me.androidbox.presentation.di

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import me.androidbox.presentation.common.SchedulerProvider

class TestSchedulerProviderImp : SchedulerProvider {
    override fun backgroundScheduler(): Scheduler = Schedulers.trampoline()

    override fun uiScheduler(): Scheduler = Schedulers.trampoline()
}
