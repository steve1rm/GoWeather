package me.androidbox.presentation.utils

import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {
    private const val RESOURCE = "GLOBAL_GO_WEATHER"

    @JvmField
    val countingIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        countingIdlingResource.increment()
        println("########## increment")
    }

    fun decrement() {
        if(!countingIdlingResource.isIdleNow) {
            countingIdlingResource.decrement()
            println("########## decrement")
        }
    }
}
