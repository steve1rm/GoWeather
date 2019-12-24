package androidbox.me.entities.response

import com.google.gson.annotations.SerializedName

data class DayEntity(
    @SerializedName("avgtemp_c") val averageTemperatureInCelsius: Float)
