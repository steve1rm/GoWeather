package androidbox.me.entities.response

import com.google.gson.annotations.SerializedName


data class CurrentWeatherEntity(
    @SerializedName("data") val currentWeatherData: List<CurrentWeatherData>)
