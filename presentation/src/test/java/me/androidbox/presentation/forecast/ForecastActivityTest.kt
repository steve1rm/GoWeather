package me.androidbox.presentation.forecast

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import me.androidbox.presentation.common.LocationUtils
import me.androidbox.presentation.di.DaggerTestPresentationComponent
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class ForecastActivityTest {
    private lateinit var forecastActivityScenario: ActivityScenario<ForecastActivity>

    @Inject
    lateinit var locationUtils: LocationUtils

    @Before
    fun setUp() {
        DaggerTestPresentationComponent
            .builder()
            .build()
            .inject(this)

        forecastActivityScenario = ActivityScenario.launch(ForecastActivity::class.java)
        forecastActivityScenario.moveToState(Lifecycle.State.DESTROYED)
        assertThat(locationUtils).isNotNull
    }

    @Test
    fun `forecastActivityScenario should not be null`() {
        assertThat(forecastActivityScenario).isNotNull

        forecastActivityScenario = ActivityScenario.launch(ForecastActivity::class.java)
        forecastActivityScenario.moveToState(Lifecycle.State.CREATED)
    }
}
