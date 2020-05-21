package androidbox.me.local.dao

import androidbox.me.local.DatabaseService
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
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


}