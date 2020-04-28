package me.androidbox.presentation.pages

import me.androidbox.presentation.screens.ForecastListScreen
import me.androidbox.presentation.screens.ForecastListScreen.Item

object ForecastListPage {

    private val forecastListScreen: ForecastListScreen by lazy {
        ForecastListScreen()
    }

    fun shouldBeVisible(): ForecastListPage = apply {
        forecastListScreen {
            dailyForecast {
                isDisplayed()
            }
        }
    }

    fun shouldHaveSize(size: Int): ForecastListPage = apply {
        forecastListScreen {
            dailyForecast {
                hasSize(size)
            }
        }
    }

    fun shouldWeatherItemAtPosition(position: Int, day: String, description: String, highTemp: String, lowTemp: String) {
        forecastListScreen {
            dailyForecast {
                childAt<Item>(position) {
                    weekDay.hasText(day)
                    weekDay.isDisplayed()

                    weatherDescription.hasText(description)
                    weatherDescription.isDisplayed()

                    lowTemperature.hasText(lowTemp)
                    lowTemperature.isDisplayed()

                    highTemperature.hasText(highTemp)
                    highTemperature.isDisplayed()
                }
            }
        }
    }
}
