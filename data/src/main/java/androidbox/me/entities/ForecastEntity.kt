package androidbox.me.entities

import androidbox.me.entities.request.WeatherEntity
import com.google.gson.annotations.SerializedName

data class ForecastEntity(
    @SerializedName("temp") val temp: Float,
    @SerializedName("high_temp") val highTemp: Float,
    @SerializedName("low_temp") val lowTemp: Float,
    @SerializedName("app_min_temp") val feelsLikeMinTemp: Float,
    @SerializedName("app_max_temp") val feelsLikeMaxTemp: Float,
    @SerializedName("valid_date") val validDate: String,
    @SerializedName("weather") val weather: WeatherEntity)
