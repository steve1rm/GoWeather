package me.androidbox.presentation.common

interface LocationUtils {
    fun isLocationServicesEnabled(): Boolean
    fun getLocationCoordinates(locationResultStatus: (LocationUtilsImp.LocationStatus) -> Unit)
    fun requestPermissionResults(locationResultStatus: (LocationUtilsImp.LocationStatus) -> Unit, requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
}
