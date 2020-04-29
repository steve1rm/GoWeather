package androidbox.me.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        ForecastTable::class
    ],
    version = 1,
    exportSchema = false
)
abstract class DatabaseService : RoomDatabase() {

    abstract fun forecastDao(): ForecastDao
}
