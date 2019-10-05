package me.androidbox.presentation.common

interface LocationUtils {
    fun isLocationServicesEnabled(): Boolean
    fun getLocationCoordinates()
    fun requestPermissionResults(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
}
