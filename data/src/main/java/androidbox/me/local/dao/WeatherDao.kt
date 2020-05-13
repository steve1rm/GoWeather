package androidbox.me.local.dao

import androidbox.me.local.tables.WeatherTable
import androidx.room.*
import io.reactivex.Single

@Dao
interface WeatherDao : BaseDao<WeatherTable> {
    @Query("SELECT * FROM weatherTable")
    fun getAllWeather(): Single<List<WeatherTable>>

    @Query("SELECT * FROM weatherTable WHERE id = :id LIMIT 1")
    fun getWeatherById(id: Long): Single<WeatherTable>

    @Query("SELECT count(*) FROM weatherTable")
    fun count(): Single<Int>
}
