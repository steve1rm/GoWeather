package androidbox.me.entities

import androidbox.me.entities.request.WeatherEntity
import com.google.gson.annotations.SerializedName

data class CurrentEntity(
    @SerializedName("city_name") val cityName: String,
    @SerializedName("state_code") val stateCode: String,
    @SerializedName("app_temp") val feelsLikeTemperature: String,
    @SerializedName("temp") val temperature: Float,
    @SerializedName("weather") val weather: WeatherEntity)
