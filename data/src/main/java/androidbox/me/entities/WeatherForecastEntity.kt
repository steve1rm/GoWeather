package androidbox.me.entities

import com.google.gson.annotations.SerializedName

data class WeatherForecastEntity(
    @SerializedName("data") val forecast: List<ForecastEntity>,
    @SerializedName("city_name") val cityName: String,
    @SerializedName("timezone") val timeZone: String,
    @SerializedName("country_code") val countryCode: String,
    @SerializedName("state_code") val stateCode: String)