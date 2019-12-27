package androidbox.me.entities.request

import me.androidbox.wrappers.Latitude
import me.androidbox.wrappers.Longitude

data class ForecastRequestEntity(val latitude: Latitude,
                                 val longitude: Longitude,
                                 val days: Int)


