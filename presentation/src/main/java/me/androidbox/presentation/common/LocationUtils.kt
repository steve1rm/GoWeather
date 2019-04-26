package me.androidbox.presentation.common

import android.app.Activity

interface LocationUtils {
    fun isLocationServicesEnabled(activity: Activity): Boolean
    fun getLocationCoordinates(activity: Activity)
    fun setLocationListener(locationUtilsListener: LocationUtilsListener)
    fun requestPermissionResults(activity: Activity, requestCode: Int, permissions: Array<out String>, grantResults: IntArray)
    fun removeLocationListener()
}
