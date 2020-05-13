package androidbox.me.local.dao

import androidbox.me.local.tables.WeatherForecastTable
import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Single

@Dao
abstract class WeatherForecastDao : BaseDao<WeatherForecastTable> {

    @Query("SELECT * FROM weatherForecastTable")
    abstract fun getAllWeather(): Single<List<WeatherForecastTable>>

    @Query("SELECT * FROM weatherForecastTable WHERE id = :id LIMIT 1")
    abstract fun getWeatherById(id: Long): Single<WeatherForecastTable>

    @Query("SELECT count(*) FROM weatherForecastTable")
    abstract fun count(): Single<Int>
}
