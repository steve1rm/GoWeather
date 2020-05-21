package androidbox.me.mockdata

import androidbox.me.local.tables.WeatherForecastTable

object WeatherForecastFactory {

    fun createWeatherForecastList(count: Int): MutableList<WeatherForecastTable> {
        val weatherForecastList = mutableListOf<WeatherForecastTable>()

        (0 until count).forEach {
            weatherForecastList.add(createWeatherForecast(it.toLong()))
        }

        return weatherForecastList
    }

    fun createWeatherForecast(forecastId: Long = 0): WeatherForecastTable =
        WeatherForecastTable(
            cityName = MockDataProvider.getString(),
            timeZone = MockDataProvider.getString(),
            countryCode = MockDataProvider.getString(),
            stateCode = MockDataProvider.getString(),
            forecastId = forecastId.inc())
}