package me.androidbox.models.request

import me.androidbox.wrappers.Latitude
import me.androidbox.wrappers.Longitude

data class ForecastRequestModel(val latitude: Latitude,
                                val longitude: Longitude,
                                val days: Int = 0)

class ForecastRequestModelBuilder {

    var latitude: Latitude = Latitude(0.0)
    var longitude: Longitude = Longitude(0.0)
    var days: Int = 0

    fun build(): ForecastRequestModel =
        ForecastRequestModel(
            latitude,
            longitude,
            days
        )
}
