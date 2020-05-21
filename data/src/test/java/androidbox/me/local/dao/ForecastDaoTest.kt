package androidbox.me.local.dao

import androidbox.me.local.DatabaseService
import androidbox.me.local.tables.ForecastTable
import androidbox.me.mockdata.ForecastFactory
import androidbox.me.mockdata.WeatherFactory
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ForecastDaoTest {

    @JvmField
    @Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database: DatabaseService by lazy {
        Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), DatabaseService::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun before() {
        database.clearAllTables()
        database.close()
    }

    @Test
    fun `should insert in forecast`() {
        // Arrange
        val weatherTable = WeatherFactory.createWeather()
        val forecastTable = ForecastFactory.createForecast()

        database.weatherDao().insert(weatherTable).test()

        // Act
        val testObserver = database.forecastDao().insert(forecastTable.copy(weatherId = 1L)).test()

        // Assert
        testObserver
            .assertComplete()
            .assertValue(1L)
    }

    @Test
    fun `should insert many forecast tables`() {
        // Arrange
        val weatherTableOne = WeatherFactory.createWeather()
        val weatherTableTwo = WeatherFactory.createWeather()
        val weatherTableThree = WeatherFactory.createWeather()

        val forecastTableOne = ForecastFactory.createForecast().copy(weatherId = 1)
        val forecastTableTwo = ForecastFactory.createForecast().copy(weatherId = 2)
        val forecastTableThree = ForecastFactory.createForecast().copy(weatherId = 3)

        database.weatherDao().insert(weatherTableOne, weatherTableTwo, weatherTableThree).test()

        // Act
        val testObserver = database.forecastDao().insert(
            forecastTableOne, forecastTableTwo, forecastTableThree).test()

        // Assert
        testObserver
            .assertComplete()
            .assertValue(listOf(1L, 2L, 3L))
    }

    @Test
    fun `should insert a list of forecast`() {
        // Arrange
        val weatherTableList = WeatherFactory.createWeatherList(10)
        val countList = listOf<Long>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        val forecastTableList = ForecastFactory.createForecastList(10)

        database.weatherDao().insert(weatherTableList).test()

        // Act
        val testObserver = database.forecastDao().insert(forecastTableList).test()

        // Assert
        testObserver
            .assertComplete()
            .assertValue(countList)
    }

    @Test
    fun `should retrieve forecast`() {
        // Arrange
        val weatherTableList = WeatherFactory.createWeather()
        val forecastTable = ForecastFactory.createForecast()
        val forecastTableList = listOf(forecastTable.copy(id = 1))

        database.weatherDao().insert(weatherTableList).test()
        database.forecastDao().insert(forecastTable).test()

        // Act
        val testObserver = database.forecastDao().getAllForecast().test()

        // Assert
        testObserver
            .assertComplete()
            .assertValue(forecastTableList)
    }
}
