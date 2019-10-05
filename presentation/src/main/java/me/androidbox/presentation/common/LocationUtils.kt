package me.androidbox.presentation.common

interface LocationUtils {
    fun isLocationServicesEnabled(): Boolean
    fun getLocationCoordinates()
    fun setLocationListener(locationUtilsListener: LocationUtilsListener)
    fun requestPermissionResults(requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
    fun removeLocationListener()
}
