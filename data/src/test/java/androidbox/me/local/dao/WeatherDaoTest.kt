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
        Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), DatabaseService::class.java)
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
            .assertValue(1)
    }

    @Test
    fun `should insert many weather tables`() {
        // Arrange
        val weatherTableOne = WeatherFactory.createWeather()
        val weatherTableTwo = WeatherFactory.createWeather()
        val weatherTableThree = WeatherFactory.createWeather()
        val countList = listOf<Long>(1, 2, 3)

        // Act
        val testObserver = database.weatherDao().insert(
            weatherTableOne,
            weatherTableTwo,
            weatherTableThree).test()

        // Assert
        testObserver
            .assertComplete()
            .assertValue(countList)
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
            .assertValue(countList)
    }

    @Test
    fun `should retrieve weather`() {
        // Arrange
        val weatherTable = WeatherFactory.createWeather()
        val weatherList = listOf(weatherTable.copy(id = 1))

        database.weatherDao().insert(weatherTable).test()

        // Act
        val testObserver = database.weatherDao().getAllWeather().test()

        // Assert
        testObserver
            .assertComplete()
            .assertValue(weatherList)
    }

    @Test
    fun `should get weather with specific ID`() {
        // Arrange
        val weatherTableOne = WeatherFactory.createWeather()
        val weatherTableTwo = WeatherFactory.createWeather()
        val weatherTableThree = WeatherFactory.createWeather()

        database.weatherDao().insert(weatherTableOne, weatherTableTwo, weatherTableThree).test()

        // Act
        val testObserver = database.weatherDao().getWeatherById(2).test()

        // Assert
        testObserver
            .assertComplete()
            .assertValue(weatherTableTwo.copy(2))
    }

    @Test
    fun `should count the correct number of record in the weather table`() {
        // Arrange
        val weatherTableList = WeatherFactory.createWeatherList(10)
        database.weatherDao().insert(weatherTableList).test()

        // Act
        val testObserver = database.weatherDao().count().test()

        // Assert
        testObserver
            .assertComplete()
            .assertValue(10)
    }

    @Test
    fun `should delete a weather record from the weather table and return the correct count`() {
        // Arrange
        val weatherTableOne = WeatherFactory.createWeather()
        val weatherTableTwo = WeatherFactory.createWeather()
        val weatherTableThree = WeatherFactory.createWeather()

        database.weatherDao().insert(weatherTableOne, weatherTableTwo, weatherTableThree).test()
        database.weatherDao().delete(weatherTableTwo.copy(id = 2)).test()

        // Act
        val testObserver = database.weatherDao().count().test()

        // Assert
        testObserver
            .assertComplete()
            .assertValue(2)
    }

    @Test
    fun `should delete a record from the weather table`() {
        // Arrange
        val weatherTableOne = WeatherFactory.createWeather()
        val weatherTableTwo = WeatherFactory.createWeather()
        val weatherTableThree = WeatherFactory.createWeather()

        database.weatherDao().insert(weatherTableOne, weatherTableTwo, weatherTableThree).test()

        // Act
        val testObserver = database.weatherDao().delete(weatherTableTwo.copy(id = 2)).test()

        // Assert
        testObserver
            .assertComplete()
            .assertValue(1)
    }

    @Test
    fun `should update a record in the weather table`() {
        // Arrange
        val weatherTableOne = WeatherFactory.createWeather()
        val weatherTableTwo = WeatherFactory.createWeather()
        val weatherTableThree = WeatherFactory.createWeather()
        val weatherTableTwoUpdated = WeatherFactory.createWeather()

        database.weatherDao().insert(weatherTableOne, weatherTableTwo, weatherTableThree).test()

        // Act
        val testObserver = database.weatherDao()
            .update(weatherTableTwoUpdated.copy(id = 2, description = "This has been updated")).test()

        // Assert
        testObserver
            .assertComplete()
            .assertValue(1)
    }
}
