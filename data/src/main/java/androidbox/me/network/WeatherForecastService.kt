package androidbox.me.network

import androidbox.me.entities.WeatherForecastEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherForecastService {
    @GET("v2.0/forecast/daily")
    fun forecast(@Query("key") key: String,
                 @Query("lat") latitude: Double,
                 @Query("lon") longitude: Double,
                 @Query("days") days: Int): Single<WeatherForecastEntity>
}
