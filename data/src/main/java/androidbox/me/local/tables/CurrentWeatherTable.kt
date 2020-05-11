package androidbox.me.local.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currentWeatherTable")
class CurrentWeatherTable(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0)
