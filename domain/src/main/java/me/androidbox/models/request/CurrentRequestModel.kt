package me.androidbox.models.request

import me.androidbox.wrappers.Latitude
import me.androidbox.wrappers.Longitude

data class CurrentRequestModel(val latitude: Latitude,
                               val longitude: Longitude)
