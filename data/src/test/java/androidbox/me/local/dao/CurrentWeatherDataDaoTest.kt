package androidbox.me.local.dao

import androidbox.me.local.DatabaseService
import androidbox.me.mockdata.CurrentWeatherDataFactory
import androidbox.me.mockdata.WeatherFactory
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CurrentWeatherDataDaoTest {

    @JvmField
    @Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database: DatabaseService by lazy {
        Room.inMemoryDatabaseBuilder(getApplicationContext(), DatabaseService::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @Before
    fun setUp() {
        database.clearAllTables()
        database.close()
    }

    @Test
    fun `should insert in current weather data table`() {
        // Arrange
        val weatherTable = WeatherFactory.createWeather()
        val currentWeatherDataTable = CurrentWeatherDataFactory.createCurrentWeatherData()

        database.weatherDao().insert(weatherTable).test()

        // Act & Assert
        database.currentWeatherDataDao().insert(currentWeatherDataTable.copy(id = 1L))
            .test()
            .assertComplete()
            .assertValue(1L)
    }

    @Test
    fun `should insert many current weather data`() {
        // Arrange
        val weatherTableOne = WeatherFactory.createWeather()
        val weatherTableTwo = WeatherFactory.createWeather()
        val weatherTableThree = WeatherFactory.createWeather()

        val currentWeatherDataTableOne = CurrentWeatherDataFactory.createCurrentWeatherData()
        val currentWeatherDataTableTwo = CurrentWeatherDataFactory.createCurrentWeatherData()
        val currentWeatherDataTableThree = CurrentWeatherDataFactory.createCurrentWeatherData()

        database.weatherDao().insert(weatherTableOne, weatherTableTwo, weatherTableThree).test()

        // Act & Assert
        database.currentWeatherDataDao().insert(currentWeatherDataTableOne, currentWeatherDataTableTwo, currentWeatherDataTableThree)
            .test()
            .assertComplete()
            .assertValue(listOf(1L, 2L, 3L))
    }

    @Test
    fun `should insert a list of current weather data`() {
        // Arrange
        val weatherTableList = WeatherFactory.createWeatherList(10)
        val currentWeatherDataList = CurrentWeatherDataFactory.createCurrentWeatherDataList(10)
        val countList = listOf<Long>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        database.weatherDao().insert(weatherTableList).test()

        // Act & Assert
        database.currentWeatherDataDao().insert(currentWeatherDataList)
            .test()
            .assertComplete()
            .assertValue(countList)
    }

    @Test
    fun `should get weather forecast with specific ID`() {
        // Arrange
        val weatherTableList = WeatherFactory.createWeatherList(10)
        val currentWeatherDataList = CurrentWeatherDataFactory.createCurrentWeatherDataList(10)

        database.weatherDao().insert(weatherTableList).test()
        database.currentWeatherDataDao().insert(currentWeatherDataList).test()

        // Act & Assert
        database.currentWeatherDataDao().getWeatherById(2).test()
            .assertComplete()
            .assertValue(currentWeatherDataList[1].copy(id = 2))
    }

    @Test
    fun `should count the correct number of records inserted in current weather data table`() {
        // Arrange
        val weatherTableList = WeatherFactory.createWeatherList(10)
        val currentWeatherDataList = CurrentWeatherDataFactory.createCurrentWeatherDataList(10)

        database.weatherDao().insert(weatherTableList).test()
        database.currentWeatherDataDao().insert(currentWeatherDataList).test()

        // Act & Assert
        database.currentWeatherDataDao().count()
            .test()
            .assertComplete()
            .assertValue(10)
    }

    @Test
    fun `should delete a record from the weather forecast table`() {
        // Arrange
        val weatherTableList = WeatherFactory.createWeatherList(10)
        val currentWeatherDataList = CurrentWeatherDataFactory.createCurrentWeatherDataList(10)

        database.weatherDao().insert(weatherTableList).test()
        database.currentWeatherDataDao().insert(currentWeatherDataList).test()

        // Act & Assert
        database.currentWeatherDataDao().delete(currentWeatherDataList[5].copy(id = 6L))
            .test()
            .assertComplete()
            .assertValue(1)
    }

    @Test
    fun `should update a record in the current weather table`() {
        // Arrange
        val weatherTableList = WeatherFactory.createWeatherList(10)
        val currentWeatherDataList = CurrentWeatherDataFactory.createCurrentWeatherDataList(10)

        database.weatherDao().insert(weatherTableList).test()
        database.currentWeatherDataDao().insert(currentWeatherDataList).test()

        val currentWeatherDataTable = currentWeatherDataList[5].copy(id = 6, stateCode = "Rainy with a chance of meatballs")

        // Act & Assert
        database.currentWeatherDataDao().update(currentWeatherDataTable)
            .test()
            .assertComplete()
            .assertValue(1)

        database.currentWeatherDataDao().getWeatherById(id = 6)
            .test()
            .assertValue(currentWeatherDataTable)
    }
}

