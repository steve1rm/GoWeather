package me.androidbox.presentation.common

interface LocationUtilsListener {
    fun onLocationSuccess(latitude: Double, longitude: Double)
    fun onLocationFailure(message: String)
}
