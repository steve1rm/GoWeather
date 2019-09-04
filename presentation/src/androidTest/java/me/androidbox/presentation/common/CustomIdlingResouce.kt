package me.androidbox.presentation.common

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.IdlingResource.ResourceCallback
import okhttp3.Dispatcher
import okhttp3.OkHttpClient

class CustomIdlingResouce private constructor(private val name: String, private val dispatcher: Dispatcher) : IdlingResource {

    private var callback: ResourceCallback? = null

    init {
        dispatcher.setIdleCallback {
            val callback: ResourceCallback? = this.callback

            callback?.let {
                callback.onTransitionToIdle()
            }
        }
    }

    companion object {
        fun create(name: String, client: OkHttpClient): CustomIdlingResouce {
            return CustomIdlingResouce(name, client.dispatcher())
        }
    }

    override fun getName(): String {
        return name
    }

    override fun isIdleNow(): Boolean {
        val idle = (dispatcher.runningCallsCount() == 0)

        if(idle && callback != null) {
            callback?.onTransitionToIdle()
        }

        return idle
    }

    override fun registerIdleTransitionCallback(callback: ResourceCallback?) {
        this.callback = callback
    }
}