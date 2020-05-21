@file:JvmName("CurrentWeatherDataFactory")

package androidbox.me.mockdata

import androidbox.me.local.tables.CurrentWeatherDataTable
import androidbox.me.mockdata.MockDataProvider.getFloat
import androidbox.me.mockdata.MockDataProvider.getString

object CurrentWeatherDataFactory {
    fun createCurrentWeatherDataList(count: Int): MutableList<CurrentWeatherDataTable> {
        val currentWeatherDataTableList = mutableListOf<CurrentWeatherDataTable>()

        (0 until count).forEach {
            currentWeatherDataTableList.add(createCurrentWeatherData(it.toLong()))
        }

        return currentWeatherDataTableList
    }

    fun createCurrentWeatherData(weatherId: Long = 0): CurrentWeatherDataTable =
        CurrentWeatherDataTable(
            stateCode = getString(),
            appTemp = getString(),
            temperature = getFloat(),
            weatherId = weatherId.inc())
}
