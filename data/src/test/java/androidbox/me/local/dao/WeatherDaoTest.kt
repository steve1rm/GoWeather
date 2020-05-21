package androidbox.me.local.dao

import androidbox.me.local.DatabaseService
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
class WeatherDaoTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    private val database: DatabaseService by lazy {
        Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
        DatabaseService::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun cleanUp() {
        database.clearAllTables()
        database.close()
    }

    @Test
    fun `should insert weather`() {
        // Arrange
        val weatherTable = WeatherFactory.createWeather()

        // Act
        val testObserver = database.weatherDao().insert(weatherTable).test()

        // Assert
        testObserver
            .assertComplete()
            .assertValueCount(1)
            .assertValue(1)
    }

    @Test
    fun `should insert a list of weather`() {
        // Arrange
        val weatherTableList = WeatherFactory.createWeatherList(10)
        val countList = listOf<Long>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        // Act
        val testObserver = database.weatherDao().insert(weatherTableList).test()

        // Assert
        testObserver
            .assertComplete()
            .assertValueCount(1)
            .assertValue(countList)
    }


    @Test
    fun `should retrieve weather`() {
        // Arrange
        val weatherTable = WeatherFactory.createWeather()
        val weatherList = listOf(weatherTable.copy(id = 1))

        // Act
        database.weatherDao().insert(weatherTable).test()
        val testObserver = database.weatherDao().getAllWeather().test()

        // Assert
        testObserver
            .assertComplete()
            .assertValueCount(1)
            .assertValue(weatherList)
    }
}
