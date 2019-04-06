package androidbox.me.entities

import com.google.gson.annotations.SerializedName

data class CurrrentEntity(
    @SerializedName("temp_c") val temperatureInCelsius: Float)
