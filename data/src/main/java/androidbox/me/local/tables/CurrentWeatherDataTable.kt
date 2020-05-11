package androidbox.me.local.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "currentWeatherDataTable",
    foreignKeys = [
        ForeignKey(
            entity = CurrentWeatherTable::class,
            parentColumns = ["id"],
            childColumns = ["currentWeatherId"],
            onDelete = ForeignKey.CASCADE
        )])
class CurrentWeatherDataTable(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "stateCode")
    val stateCode: String,

    @ColumnInfo(name = "appTemp")
    val appTemp: String,

    @ColumnInfo(name = "temperature")
    val temperature: Float,

    @ColumnInfo(name = "currentWeatherId")
    val currentWeatherId: Long
)

