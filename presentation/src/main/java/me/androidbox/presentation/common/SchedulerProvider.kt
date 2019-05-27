package me.androidbox.presentation.common

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun backgroundScheduler(): Scheduler
    fun uiScheduler(): Scheduler
}
