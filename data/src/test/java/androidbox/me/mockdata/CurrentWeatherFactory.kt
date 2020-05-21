package androidbox.me.mockdata

import androidbox.me.local.tables.CurrentWeatherTable

object CurrentWeatherFactory {
    fun createCurrentWeatherDataList(count: Int): MutableList<CurrentWeatherTable> {
        val currentWeatherList = mutableListOf<CurrentWeatherTable>()

        (0 until count).forEach {
            currentWeatherList.add(createCurrentWeather(it.toLong()))
        }

        return currentWeatherList
    }

    fun createCurrentWeather(currentWeatherDataId: Long = 0): CurrentWeatherTable =
        CurrentWeatherTable(currentWeatherDataId = currentWeatherDataId.inc())
}