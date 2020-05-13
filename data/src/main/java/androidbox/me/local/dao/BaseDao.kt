package androidbox.me.local.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import io.reactivex.Single


interface BaseDao<T> {

    @Insert
    fun insert(table: T): Single<Long>

    @Insert
    fun insert(vararg table: T): Single<List<Long>>

    @Update
    fun update(table: T): Single<Int>

    @Delete
    fun delete(table: T): Single<Int>
}
