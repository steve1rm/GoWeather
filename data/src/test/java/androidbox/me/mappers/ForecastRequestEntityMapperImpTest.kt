package androidbox.me.mappers

import me.androidbox.models.ForecastRequestModel
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class ForecastRequestEntityMapperImpTest {

    private lateinit var forecastRequestEntityMapper: ForecastRequestEntityMapper

    @Before
    fun setUp() {
        forecastRequestEntityMapper = ForecastRequestEntityMapperImp()
        assertThat(forecastRequestEntityMapper).isNotNull
    }

    @Test
    fun `should map domain to entity`() {
        val forecastRequestModel = createForecastRequestModel()

        val forecastRequestEntity = forecastRequestEntityMapper.map(forecastRequestModel)

        assertThat(forecastRequestEntity).isEqualToComparingFieldByField(forecastRequestEntity)
    }

    private fun createForecastRequestModel(): ForecastRequestModel =
        ForecastRequestModel(34.9898453, -68.4786847, 4)
}
