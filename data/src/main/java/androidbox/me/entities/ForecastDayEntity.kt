package androidbox.me.entities

import com.google.gson.annotations.SerializedName

data class ForecastDayEntity(
    @SerializedName("date") val date: String,
    @SerializedName("date_epoch") val dateEpoch: String,
    @SerializedName("day") val day: DayEntity)
