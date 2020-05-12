package androidbox.me.local.dao

import androidbox.me.local.tables.ForecastTable
import androidx.room.*
import io.reactivex.Single

@Dao
interface ForecastDao : BaseDao<ForecastTable> {

    override fun insert(table: ForecastTable): Single<Long>

    override fun insert(vararg table: ForecastTable): Single<List<Long>>

    override fun update(table: ForecastTable): Single<Int>

    override fun delete(table: ForecastTable): Single<Long>

    @Query("SELECT * FROM forecastTable")
    fun getAllForecast(): Single<List<ForecastTable>>

    @Query("SELECT * FROM forecastTable WHERE id = :id LIMIT 1")
    fun getForecastById(id: Long): Single<ForecastTable>

    @Query("SELECT count(*) FROM forecastTable")
    fun count(): Single<Int>
}
