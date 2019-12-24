package androidbox.me.network

import androidbox.me.entities.CurrentEntity
import androidbox.me.entities.WeatherForecastEntity
import io.reactivex.Single
import me.androidbox.wrappers.Latitude
import me.androidbox.wrappers.Longitude
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherForecastService {
    @GET("v2.0/forecast/daily")
    fun forecast(@Query("key") key: String,
                 @Query("lat") latitude: Double,
                 @Query("lon") longitude: Double,
                 @Query("days") days: Int): Single<WeatherForecastEntity>

    @GET("v2.0/current")
    fun current(@Query("key") key: String,
                @Query("lat") latitude: Latitude,
                @Query("lon") longitude: Longitude): Single<CurrentEntity>
}
