package me.androidbox.models

data class ForecastRequestModel(val latitude: Float = 0F,
                                val longitude: Float = 0F,
                                val days: Int = 0)

class ForecastRequestModelBuilder {

    var latitude: Float = 0F
    var longitude: Float = 0F
    var days: Int = 0

    fun build(): ForecastRequestModel =
        ForecastRequestModel(latitude, longitude, days)
}
