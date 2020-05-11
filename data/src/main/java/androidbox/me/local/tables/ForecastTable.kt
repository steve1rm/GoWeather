package androidbox.me.local.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "forecastTable")
data class ForecastTable(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "temp")
    val temp: Float,

    @ColumnInfo(name = "highTemp")
    val highTemp: Float,

    @ColumnInfo(name = "lowTemp")
    val lowTemp: Float,

    @ColumnInfo(name = "feelLikeMinTemp")
    val feelLikeMinTemp: Float,

    @ColumnInfo(name = "feelLikeMaxTemp")
    val feelLikeMaxTemp: Float,

    @ColumnInfo(name = "validDate")
    val validDate: String
)
