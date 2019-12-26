package androidbox.me.entities.response

import com.google.gson.annotations.SerializedName


data class DataEntity(
    @SerializedName("data") val currentEntity: List<CurrentEntity>)

data class CurrentEntity(
    @SerializedName("city_name") val cityName: String,
    @SerializedName("state_code") val stateCode: String,
    @SerializedName("app_temp") val feelsLikeTemperature: String,
    @SerializedName("temp") val temperature: Float,
    @SerializedName("weather") val weather: WeatherEntity)


