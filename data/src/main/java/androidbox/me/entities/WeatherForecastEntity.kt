package androidbox.me.entities

import com.google.gson.annotations.SerializedName

data class WeatherForecastEntity(
    @SerializedName("location") val location: LocationEntity,
    @SerializedName("current") val current: CurrrentEntity,
    @SerializedName("forecast") val forecast: ForecastEntity)
