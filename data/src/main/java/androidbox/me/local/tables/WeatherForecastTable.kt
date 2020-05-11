package androidbox.me.local.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weatherForecastTable")
data class WeatherForecastTable(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "cityName")
    val cityName: String,

    @ColumnInfo(name = "timeZone")
    val timeZone: String,

    @ColumnInfo(name = "countryCode")
    val countryCode: String,

    @ColumnInfo(name = "stateCode")
    val stateCode: String)
