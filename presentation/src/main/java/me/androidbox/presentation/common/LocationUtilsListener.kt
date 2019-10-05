package me.androidbox.presentation.common

import me.androidbox.presentation.common.LocationUtilsImp.LocationStatus

interface LocationUtilsListener {
    fun onLocationResult(locationStatus: LocationStatus)
}
