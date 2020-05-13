package androidbox.me.local.dao

import androidbox.me.local.tables.CurrentWeatherDataTable
import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Single

@Dao
abstract class CurrentWeatherDataDao : BaseDao<CurrentWeatherDataTable> {
    @Query("SELECT * FROM currentWeatherDataTable")
    abstract fun getAllWeather(): Single<List<CurrentWeatherDataTable>>

    @Query("SELECT * FROM currentWeatherDataTable WHERE id = :id LIMIT 1")
    abstract fun getWeatherById(id: Long): Single<CurrentWeatherDataTable>

    @Query("SELECT count(*) FROM currentWeatherDataTable")
    abstract fun count(): Single<Int>
}
