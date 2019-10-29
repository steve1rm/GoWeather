package me.androidbox.presentation.utils

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkHelper @Inject constructor(private val context: Context) {

    fun isNetworkConnected() = false
}
