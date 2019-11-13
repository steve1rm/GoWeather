package androidbox.me.entities

import com.google.gson.annotations.SerializedName

data class ForecastEntity(
    @SerializedName("high_temp") val highTemp: Float,
    @SerializedName("app_min_temp") val minTemp: Float,
    @SerializedName("app_max_temp") val maxTemp: Float)
