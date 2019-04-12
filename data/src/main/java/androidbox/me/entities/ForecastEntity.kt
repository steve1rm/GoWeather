package androidbox.me.entities

import com.google.gson.annotations.SerializedName

data class ForecastEntity(
    @SerializedName("forecastday") val forecastDay: List<ForecastDayEntity>)
