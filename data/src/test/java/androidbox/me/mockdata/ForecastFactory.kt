package androidbox.me.mockdata

import androidbox.me.local.tables.ForecastTable
import androidbox.me.mockdata.MockDataProvider.getFloat
import androidbox.me.mockdata.MockDataProvider.getString

object ForecastFactory {

    fun createForecastList(count: Int): MutableList<ForecastTable> {
        val forecastList = mutableListOf<ForecastTable>()

        (0 until count).forEach {
            forecastList.add(createForecast(it.toLong()))
        }

        return forecastList
    }

    fun createForecast(weatherId: Long = 0): ForecastTable =
        ForecastTable(
            temp = getFloat(),
            highTemp = getFloat(),
            lowTemp = getFloat(),
            feelLikeMaxTemp = getFloat(),
            feelLikeMinTemp = getFloat(),
            validDate = getString(),
            weatherId = weatherId.inc())
}
