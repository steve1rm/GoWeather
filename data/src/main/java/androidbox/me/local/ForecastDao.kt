package androidbox.me.local

import androidx.room.*
import io.reactivex.Single

@Dao
interface ForecastDao {

    @Insert
    fun insert(forecastTable: ForecastTable): Single<Long>

    @Update
    fun update(forecastTable: ForecastTable): Single<Int>

    @Delete
    fun delete(forecastTable: ForecastTable): Single<Int>

    @Insert
    fun insertMany(vararg table: ForecastTable): Single<List<Long>>

    @Query("SELECT * FROM forecastTable")
    fun getAllForecast(): Single<List<ForecastTable>>

    @Query("SELECT * FROM forecastTable WHERE id = :id LIMIT 1")
    fun getForecastById(id: Long): Single<ForecastTable>

    @Query("SELECT count(*) FROM forecastTable")
    fun count(): Single<Int>
}
