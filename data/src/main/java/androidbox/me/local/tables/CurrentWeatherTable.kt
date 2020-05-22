package androidbox.me.local.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "currentWeatherTable",
    foreignKeys = [ForeignKey(
        entity = CurrentWeatherDataTable::class,
        parentColumns = ["id"],
        childColumns = ["currentWeatherDataId"],
        onDelete = ForeignKey.CASCADE)])
data class CurrentWeatherTable(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "currentWeatherDataId")
    val currentWeatherDataId: Long)

