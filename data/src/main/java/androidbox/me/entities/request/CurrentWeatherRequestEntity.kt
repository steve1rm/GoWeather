package androidbox.me.entities.request

import me.androidbox.wrappers.Latitude
import me.androidbox.wrappers.Longitude

data class CurrentWeatherRequestEntity(val latitude: Latitude,
                                       val longitude: Longitude)
