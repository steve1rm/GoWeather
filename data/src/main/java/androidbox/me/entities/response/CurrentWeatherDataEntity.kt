package androidbox.me.entities.response

import com.google.gson.annotations.SerializedName

data class CurrentWeatherData(
    @SerializedName("city_name") val cityName: String,
    @SerializedName("state_code") val stateCode: String,
    @SerializedName("app_temp") val feelsLikeTemperature: Float,
    @SerializedName("temp") val temperature: Float,
    @SerializedName("weather") val weather: WeatherEntity)


