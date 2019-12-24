package me.androidbox.models.request

data class ForecastRequestModel(val latitude: Double,
                                val longitude: Double,
                                val days: Int = 0)

class ForecastRequestModelBuilder {

    var latitude: Double = 0.0
    var longitude: Double = 0.0
    var days: Int = 0

    fun build(): ForecastRequestModel =
        ForecastRequestModel(
            latitude,
            longitude,
            days
        )
}
