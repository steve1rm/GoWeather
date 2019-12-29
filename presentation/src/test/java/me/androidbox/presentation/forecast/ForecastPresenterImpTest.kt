package me.androidbox.presentation.forecast

import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import me.androidbox.interactors.forecast.WeatherForecastInteractor
import me.androidbox.models.request.ForecastRequestModel
import me.androidbox.models.request.ForecastRequestModelBuilder
import me.androidbox.models.response.WeatherForecastModel
import me.androidbox.models.response.ForecastModel
import me.androidbox.models.response.LocationModel
import me.androidbox.presentation.common.SchedulerProvider
import me.androidbox.presentation.common.TestSchedulerProviderImp
import me.androidbox.presentation.forecast.mvp.ForecastPresenter
import me.androidbox.presentation.forecast.mvp.ForecastPresenterImp
import me.androidbox.presentation.forecast.mvp.ForecastView
import me.androidbox.presentation.mappers.WeatherForecastPresentationMapper
import me.androidbox.presentation.models.CurrentWeather
import me.androidbox.presentation.models.Forecast
import me.androidbox.presentation.models.Location
import me.androidbox.presentation.models.WeatherForecast
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test


class ForecastPresenterImpTest {
    private val weatherForecastIntIterator: WeatherForecastInteractor = mock()
    private val weatherForecastPresentationMapper: WeatherForecastPresentationMapper = mock()
    private val forecastView: ForecastView = mock()
    private lateinit var forecastPresenter: ForecastPresenter
    private lateinit var schedulerProvider: SchedulerProvider

    @Before
    fun setUp() {
        schedulerProvider = TestSchedulerProviderImp()
        forecastPresenter = ForecastPresenterImp(
            weatherForecastIntIterator,
            weatherForecastPresentationMapper,
            schedulerProvider
        )
        assertThat(forecastPresenter).isNotNull
    }

    @Test
    fun `successfully requests weather forecast`() {
        val forecastRequestModel = createForecastModel {
            latitude = 34.0
            longitude = -89.0
            days = 4
        }
        val locationModel = LocationModel(
            "name",
            "region",
            "country"
        )
        val currentModel = CurrentModel(42)
        val forecastModel =
            ForecastModel(emptyList())
        val weatherForecastModel =
            WeatherForecastModel(
                locationModel,
                currentModel,
                forecastModel
            )
        val weatherForecast = WeatherForecast(
            Location("name", "region", "country"),
            CurrentWeather(42),
            Forecast(emptyList()))

        whenever(weatherForecastIntIterator
            .requestWeatherForecast(forecastRequestModel))
            .thenReturn(Single.just(
                WeatherForecastModel(
                    locationModel,
                    currentModel,
                    forecastModel
                )
            ))

        whenever(weatherForecastPresentationMapper.map(weatherForecastModel))
            .thenReturn(weatherForecast)

        forecastPresenter.initialize(forecastView)
        forecastPresenter.requestWeatherForecast(34.0, -89.0, 4)

        verify(weatherForecastPresentationMapper).map(weatherForecastModel)
        verifyNoMoreInteractions(weatherForecastPresentationMapper)
        verify(forecastPresenter.getView())?.onForecastSuccess(weatherForecast)
        verifyNoMoreInteractions(forecastPresenter.getView())
    }

    @Test
    fun `show error message on request failure`() {

    }

    private fun createForecastModel(func: ForecastRequestModelBuilder.() -> Unit): ForecastRequestModel {
        return ForecastRequestModelBuilder().apply(func).build()
    }
}
