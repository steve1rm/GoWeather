package androidbox.me.entities.request

import com.google.gson.annotations.SerializedName

data class WeatherEntity(
    @SerializedName("icon") val icon: String,
    @SerializedName("code") val code: Int,
    @SerializedName("description") val description: String)