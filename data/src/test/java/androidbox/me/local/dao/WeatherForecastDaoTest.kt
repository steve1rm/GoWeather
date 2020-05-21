package androidbox.me.local.dao

import androidbox.me.local.DatabaseService
import androidbox.me.mockdata.ForecastFactory
import androidbox.me.mockdata.WeatherFactory
import androidbox.me.mockdata.WeatherForecastFactory
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WeatherForecastDaoTest {

    @JvmField
    @Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database: DatabaseService by lazy {
        Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), DatabaseService::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @Before
    fun setUp() {
        database.clearAllTables()
        database.close()
    }

    @Test
    fun `should insert in weatherForecast table`() {
        // Arrange
        val weatherForecastTable = WeatherForecastFactory.createWeatherForecast()
        val weatherTable = WeatherFactory.createWeather()
        val forecastTable = ForecastFactory.createForecast()

        database.weatherDao().insert(weatherTable).test()
        database.forecastDao().insert(forecastTable.copy(weatherId = 1L)).test()

        // Act
        val testObserver =
            database.weatherForecastDao().insert(weatherForecastTable.copy(forecastId = 1L)).test()

        // Assert
        testObserver
            .assertComplete()
            .assertValue(1L)
    }

    @Test
    fun `should insert many weatherForecast tables`() {
        // Arrange
        val weatherTableOne = WeatherFactory.createWeather()
        val weatherTableTwo = WeatherFactory.createWeather()
        val weatherTableThree = WeatherFactory.createWeather()

        val forecastTableOne = ForecastFactory.createForecast().copy(weatherId = 1L)
        val forecastTableTwo = ForecastFactory.createForecast().copy(weatherId = 2L)
        val forecastTableThree = ForecastFactory.createForecast().copy(weatherId = 3L)

        val weatherForecastTableOne = WeatherForecastFactory.createWeatherForecast()
        val weatherForecastTableTwo = WeatherForecastFactory.createWeatherForecast()
        val weatherForecastTableThree = WeatherForecastFactory.createWeatherForecast()

        database.weatherDao().insert(weatherTableOne, weatherTableTwo, weatherTableThree).test()
        database.forecastDao().insert(forecastTableOne, forecastTableTwo, forecastTableThree).test()

        // Act & Assert
        database.weatherForecastDao().insert(
            weatherForecastTableOne.copy(forecastId = 1L),
            weatherForecastTableTwo.copy(forecastId = 2L),
            weatherForecastTableThree.copy(forecastId = 3L)).test()
            .assertComplete()
            .assertValue(listOf(1L, 2L, 3L))
    }

    @Test
    fun `should insert a list of weather forecast`() {
        // Arrange
        val weatherTableList = WeatherFactory.createWeatherList(10)
        val forecastTableList = ForecastFactory.createForecastList(10)
        val weatherForecastTableList = WeatherForecastFactory.createWeatherForecastList(10)
        val countList = listOf<Long>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        database.weatherDao().insert(weatherTableList).test()
        database.forecastDao().insert(forecastTableList).test()

        // Act & Assert
        database.weatherForecastDao().insert(weatherForecastTableList).test()
            .assertComplete()
            .assertValue(countList)
    }

    @Test
    fun `should retrieve weather forecast`() {
        // Arrange
        val weatherTableList = WeatherFactory.createWeatherList(1)
        val forecastTableList = ForecastFactory.createForecastList(1)
        val weatherForecastTableList = WeatherForecastFactory.createWeatherForecastList(1)

        database.weatherDao().insert(weatherTableList).test()
        database.forecastDao().insert(forecastTableList).test()
        database.weatherForecastDao().insert(weatherForecastTableList).test()

        // Act & Assert
        database.weatherForecastDao().getAllWeather().test()
            .assertComplete()
            .assertValue(listOf(weatherForecastTableList[0].copy(id = 1)))
    }

    @Test
    fun `should get weather forecast with specific ID`() {
        // Arrange
        val weatherTableList = WeatherFactory.createWeatherList(3)
        val forecastTableList = ForecastFactory.createForecastList(3)
        val weatherForecastTableList = WeatherForecastFactory.createWeatherForecastList(3)

        database.weatherDao().insert(weatherTableList).test()
        database.forecastDao().insert(forecastTableList).test()
        database.weatherForecastDao().insert(weatherForecastTableList).test()

        // Act & Assert
        database.weatherForecastDao().getWeatherById(2).test()
            .assertComplete()
            .assertValue(weatherForecastTableList[1].copy(id = 2))
    }

    @Test
    fun `should count the correct number of records current inserted in weather forecast`() {
        // Arrange
        val weatherTableList = WeatherFactory.createWeatherList(10)
        val forecastTableList = ForecastFactory.createForecastList(10)
        val weatherForecastTableList = WeatherForecastFactory.createWeatherForecastList(10)

        database.weatherDao().insert(weatherTableList).test()
        database.forecastDao().insert(forecastTableList).test()
        database.weatherForecastDao().insert(weatherForecastTableList).test()

        // Act & Assert
        database.weatherForecastDao().count().test()
            .assertComplete()
            .assertValue(10)
    }

    @Test
    fun `should delete a record from the weather forecast table`() {
        // Arrange
        val weatherTableList = WeatherFactory.createWeatherList(10)
        val forecastTableList = ForecastFactory.createForecastList(10)
        val weatherForecastTableList = WeatherForecastFactory.createWeatherForecastList(10)

        database.weatherDao().insert(weatherTableList).test()
        database.forecastDao().insert(forecastTableList).test()
        database.weatherForecastDao().insert(weatherForecastTableList).test()

        // Act & Assert
        database.weatherForecastDao().delete(weatherForecastTableList[5].copy(id = 6)).test()
            .assertComplete()
            .assertValue(1)
    }

    @Test
    fun `should update a record in the weather forecast table`() {
        // Arrange
        val weatherTableList = WeatherFactory.createWeatherList(10)
        val forecastTableList = ForecastFactory.createForecastList(10)
        val weatherForecastTableList = WeatherForecastFactory.createWeatherForecastList(10)

        database.weatherDao().insert(weatherTableList).test()
        database.forecastDao().insert(forecastTableList).test()
        database.weatherForecastDao().insert(weatherForecastTableList).test()

        // Act & Assert
        database.weatherForecastDao().update(weatherForecastTableList[5].copy(id = 6, countryCode = "updated country code")).test()
            .assertComplete()
            .assertValue(1)
    }
}
