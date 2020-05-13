package androidbox.me.local.dao

import androidbox.me.local.tables.CurrentWeatherTable
import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Single

@Dao
abstract class CurrentWeatherDao : BaseDao<CurrentWeatherTable> {
    @Query("SELECT * FROM currentWeatherTable")
    abstract fun getAllWeather(): Single<List<CurrentWeatherTable>>

    @Query("SELECT * FROM currentWeatherTable WHERE id = :id LIMIT 1")
    abstract fun getWeatherById(id: Long): Single<CurrentWeatherTable>

    @Query("SELECT count(*) FROM currentWeatherTable")
    abstract fun count(): Single<Int>
}
