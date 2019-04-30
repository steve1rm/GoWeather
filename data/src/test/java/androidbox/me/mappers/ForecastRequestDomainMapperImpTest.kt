package androidbox.me.mappers

import androidbox.me.entities.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class ForecastRequestDomainMapperImpTest {

    private lateinit var forecastRequestDomainMapper: ForecastRequestDomainMapper

    @Before
    fun setUp() {
        forecastRequestDomainMapper = ForecastRequestDomainMapperImp()
        assertThat(forecastRequestDomainMapper).isNotNull
    }

    @Test
    fun `should map entity to domain model`() {
        val entity = createWeatherForecastEntity()
        val domain = forecastRequestDomainMapper.map(entity)

        assertThat(domain.location).isEqualToComparingFieldByField(entity.location)
        assertThat(domain.current.temperatureInCelsius).isEqualToComparingFieldByField(entity.current.temperatureInCelsius.toInt())
        domain.forecast.forecastDay.forEachIndexed { index, forecastDayModel ->
            assertThat(forecastDayModel.date).isEqualTo(entity.forecast.forecastDay[index].date)
            assertThat(forecastDayModel.dateEpoch).isEqualTo(entity.forecast.forecastDay[index].dateEpoch)
            assertThat(forecastDayModel.day.averageTemperatureInCelsius).isEqualTo(entity.forecast.forecastDay[index].day.averageTemperatureInCelsius)
        }
    }

    private fun createWeatherForecastEntity(): WeatherForecastEntity {
        return WeatherForecastEntity(
            createLocationEntity(),
            createCurrent(),
            createForecast())
    }

    private fun createLocationEntity(): LocationEntity {
        return LocationEntity("name", "region", "country")
    }

    private fun createCurrent(): CurrrentEntity {
        return CurrrentEntity(45.98F)
    }

    private fun createForecast(): ForecastEntity {
        return ForecastEntity(createForecastDayList())
    }

    private fun createForecastDayList(): List<ForecastDayEntity> {
        return listOf(
            ForecastDayEntity("date", "dateEpoch", createDay()),
            ForecastDayEntity("date", "dateEpoch", createDay()),
            ForecastDayEntity("date", "dateEpoch", createDay()))
    }

    private fun createDay(): DayEntity {
        return DayEntity(34.43F)
    }
}
