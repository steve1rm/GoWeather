package me.androidbox.presentation

import androidx.test.espresso.IdlingRegistry
import com.jakewharton.espresso.OkHttp3IdlingResource
import okhttp3.OkHttpClient

object IdlingResource {
    @JvmStatic
    fun registerOkHttp(okHttpClient: OkHttpClient) {
        IdlingRegistry.getInstance().register(OkHttp3IdlingResource.create("okhttp", okHttpClient))
    }
}
