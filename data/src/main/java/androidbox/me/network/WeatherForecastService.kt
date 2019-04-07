package androidbox.me.network

import androidbox.me.entities.WeatherForecastEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherForecastService {
    @GET("/forecast.json")
    fun forecast(@Query("key") key: String,
                 @Query("q") query: String,
                 @Query("days") days: Int): Single<WeatherForecastEntity>
}
