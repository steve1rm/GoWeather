package me.androidbox.presentation.common

import me.androidbox.presentation.common.LocationUtilsImp.LocationInformation

interface LocationUtilsListener {
    fun onLocationResult(locationInformation: LocationInformation)
}
