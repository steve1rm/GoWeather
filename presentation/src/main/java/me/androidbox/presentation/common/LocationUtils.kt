package me.androidbox.presentation.common

import me.androidbox.presentation.common.LocationUtilsImp.LocationStatus

interface LocationUtils {
    fun isLocationServicesEnabled(): Boolean
    fun getLocationCoordinates(locationResultStatus: (LocationStatus) -> Unit)
    fun requestPermissionResults(locationResultStatus: (LocationStatus) -> Unit,
                                 requestCode: Int,
                                 permissions: Array<out String>,
                                 grantResults: IntArray)
}
