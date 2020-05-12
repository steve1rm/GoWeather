package androidbox.me.local.dao

import androidbox.me.local.tables.WeatherTable
import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Single

@Dao
interface WeatherDao : BaseDao<WeatherTable> {

    override fun insert(table: WeatherTable): Single<Long>

    override fun insert(vararg table: WeatherTable): Single<List<Long>>

    override fun update(table: WeatherTable): Single<Int>

    override fun delete(table: WeatherTable): Single<Long>

    @Query("SELECT * FROM weatherTable")
    fun getAllWeather(): Single<List<WeatherTable>>

    @Query("SELECT * FROM weatherTable WHERE id = :id LIMIT 1")
    fun getWeatherById(id: Long): Single<WeatherTable>

    @Query("SELECT count(*) FROM weatherTable")
    fun count(): Single<Int>
}
