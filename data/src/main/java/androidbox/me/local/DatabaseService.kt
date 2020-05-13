package androidbox.me.local

import androidbox.me.local.dao.*
import androidbox.me.local.tables.*
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        ForecastTable::class,
        WeatherTable::class,
        CurrentWeatherDataTable::class,
        CurrentWeatherTable::class,
        WeatherForecastTable::class
    ],
    version = 1,
    exportSchema = false
)
abstract class DatabaseService : RoomDatabase() {

    abstract fun forecastDao(): ForecastDao

    abstract fun weatherDao(): WeatherDao

    abstract fun currentWeatherDataDao(): CurrentWeatherDataDao

    abstract fun currentWeatherDao(): CurrentWeatherDao

    abstract fun weatherForecastDao(): WeatherForecastDao
}
