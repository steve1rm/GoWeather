package androidbox.me.local.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weatherTable")
data class WeatherTable(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "icon")
    val icon: String,

    @ColumnInfo(name = "code")
    val code: Int,

    @ColumnInfo(name = "description")
    val description: String
)
