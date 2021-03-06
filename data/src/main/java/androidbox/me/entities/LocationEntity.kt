package androidbox.me.entities

import com.google.gson.annotations.SerializedName

data class LocationEntity(
    @SerializedName("name") val name: String,
    @SerializedName("region") val region: String,
    @SerializedName("country") val country: String)
