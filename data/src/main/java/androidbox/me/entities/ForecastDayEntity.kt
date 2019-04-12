package androidbox.me.entities

import com.google.gson.annotations.SerializedName

data class ForecastDayEntity(
    @SerializedName("day") val day: DayEntity)
