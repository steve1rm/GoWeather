package androidbox.me.mockdata

import androidbox.me.local.tables.WeatherTable

object WeatherFactory {

    fun createWeatherList(count: Int): List<WeatherTable> {
        val weatherTableList = mutableListOf<WeatherTable>()

        repeat(count) {
           weatherTableList.add(createWeather())
        }

        return weatherTableList
    }

    fun createWeather(): WeatherTable =
        WeatherTable(
            icon = MockDataProvider.getString(),
            code = MockDataProvider.getInt(),
            description = MockDataProvider.getString())
}
