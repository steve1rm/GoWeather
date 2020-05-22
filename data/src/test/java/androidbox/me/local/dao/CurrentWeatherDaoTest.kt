package androidbox.me.local.dao

import androidbox.me.local.DatabaseService
import androidbox.me.mockdata.CurrentWeatherDataFactory
import androidbox.me.mockdata.CurrentWeatherFactory
import androidbox.me.mockdata.WeatherFactory
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CurrentWeatherDaoTest {

    @JvmField
    @Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database: DatabaseService by lazy {
        Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            DatabaseService::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @Before
    fun setUp() {
        database.clearAllTables()
        database.close()
    }

    @Test
    fun `should insert in the currentWeather table`() {
        // Arrange
        val weatherTable = WeatherFactory.createWeather()
        val currentWeatherDataTable = CurrentWeatherDataFactory.createCurrentWeatherData()
        val currentWeatherTable = CurrentWeatherFactory.createCurrentWeather()

        database.weatherDao().insert(weatherTable).test()
        database.currentWeatherDataDao().insert(currentWeatherDataTable.copy(id = 1)).test()

        // Act & Assert
        database.currentWeatherDao().insert(currentWeatherTable.copy(id = 1))
            .test()
            .assertComplete()
            .assertValue(1L)
    }

    @Test
    fun `should insert many into currentWeather table`() {
        // Arrange
        val weatherTableOne = WeatherFactory.createWeather()
        val weatherTableTwo = WeatherFactory.createWeather()
        val weatherTableThree = WeatherFactory.createWeather()

        val currentWeatherDataTableOne = CurrentWeatherDataFactory.createCurrentWeatherData()
        val currentWeatherDataTableTwo = CurrentWeatherDataFactory.createCurrentWeatherData()
        val currentWeatherDataTableThree = CurrentWeatherDataFactory.createCurrentWeatherData()

        val currentWeatherTableOne = CurrentWeatherFactory.createCurrentWeather()
        val currentWeatherTableTwo = CurrentWeatherFactory.createCurrentWeather()
        val currentWeatherTableThree = CurrentWeatherFactory.createCurrentWeather()

        database.weatherDao().insert(weatherTableOne, weatherTableTwo, weatherTableThree).test()
        database.currentWeatherDataDao().insert(
            currentWeatherDataTableOne.copy(id = 1),
            currentWeatherDataTableTwo.copy(id = 2),
            currentWeatherDataTableThree.copy(id = 3)).test()

        // Act & Assert
        database.currentWeatherDao().insert(
            currentWeatherTableOne.copy(id = 1),
            currentWeatherTableTwo.copy(id = 2),
            currentWeatherTableThree.copy(id = 3))
            .test()
            .assertComplete()
            .assertValue(listOf(1L, 2L, 3L))
    }

    @Test
    fun `should insert a list of current weather`() {
        // Arrange
        val weatherTableList = WeatherFactory.createWeatherList(10)
        val currentWeahterDataList = CurrentWeatherDataFactory.createCurrentWeatherDataList(10)
        val currentWeather = CurrentWeatherFactory.createCurrentWeatherDataList(10)
        val countList = listOf<Long>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        database.weatherDao().insert(weatherTableList).test()
        database.currentWeatherDataDao().insert(currentWeahterDataList).test()

        // Act & Assert
        database.currentWeatherDao().insert(currentWeather)
            .test()
            .assertComplete()
            .assertValue(countList)
    }

    @Test
    fun `should get current weather with specific ID`() {
        // Arrange
        val weatherTableList = WeatherFactory.createWeatherList(10)
        val currentWeahterDataList = CurrentWeatherDataFactory.createCurrentWeatherDataList(10)
        val currentWeather = CurrentWeatherFactory.createCurrentWeatherDataList(10)

        database.weatherDao().insert(weatherTableList).test()
        database.currentWeatherDataDao().insert(currentWeahterDataList).test()
        database.currentWeatherDao().insert(currentWeather).test()

        // Act & Assert
        database.currentWeatherDao().getWeatherById(id = 4)
            .test()
            .assertComplete()
            .assertValue(currentWeather[3].copy(id = 4))
    }

    @Test
    fun `should count the correct number of records inserted in the current weather`() {
        // Arrange
        val weatherTableList = WeatherFactory.createWeatherList(10)
        val currentWeahterDataList = CurrentWeatherDataFactory.createCurrentWeatherDataList(10)
        val currentWeather = CurrentWeatherFactory.createCurrentWeatherDataList(10)

        database.weatherDao().insert(weatherTableList).test()
        database.currentWeatherDataDao().insert(currentWeahterDataList).test()
        database.currentWeatherDao().insert(currentWeather).test()

        // Act & Assert
        database.currentWeatherDao().count()
            .test()
            .assertComplete()
            .assertValue(10)
    }

    @Test
    fun `should delete a record from the current weather table`() {
        // Arrange
        val weatherTableList = WeatherFactory.createWeatherList(10)
        val currentWeahterDataList = CurrentWeatherDataFactory.createCurrentWeatherDataList(10)
        val currentWeather = CurrentWeatherFactory.createCurrentWeatherDataList(10)

        database.weatherDao().insert(weatherTableList).test()
        database.currentWeatherDataDao().insert(currentWeahterDataList).test()
        database.currentWeatherDao().insert(currentWeather).test()

        // Act & Assert
        database.currentWeatherDao().delete(currentWeather[5].copy(id = 6))
            .test()
            .assertComplete()
            .assertValue(1)
    }

    @Test
    fun `should update a record in the current weather table`() {
        // Arrange
        val weatherTableList = WeatherFactory.createWeatherList(10)
        val currentWeatherDataList = CurrentWeatherDataFactory.createCurrentWeatherDataList(10)
        val currentWeather = CurrentWeatherFactory.createCurrentWeatherDataList(10)

        database.weatherDao().insert(weatherTableList).test()
        database.currentWeatherDataDao().insert(currentWeatherDataList).test()
        database.currentWeatherDao().insert(currentWeather).test()

        // Act & Assert
        database.currentWeatherDao().update(currentWeather[5].copy(id = 6))
            .test()
            .assertComplete()
            .assertValue(1)
    }
}
