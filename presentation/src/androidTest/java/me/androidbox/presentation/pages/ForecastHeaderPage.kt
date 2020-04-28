package me.androidbox.presentation.pages

import me.androidbox.presentation.screens.ForecastHeaderScreen

object ForecastHeaderPage {

    private val forecastHeaderScreen: ForecastHeaderScreen by lazy {
        ForecastHeaderScreen()
    }

    fun whileOnForecastHeaderPage(): ForecastHeaderPage = this

    fun shouldBeVisible(): ForecastHeaderPage = apply {
        forecastHeaderScreen {
            weatherForecastHeader {
                isDisplayed()
            }
        }
    }

    fun shouldDisplayCurrentTemperature(temperature: String): ForecastHeaderPage = apply {
        forecastHeaderScreen {
            temperature {
                isDisplayed()
                hasText(temperature)
            }
        }
    }

    fun shouldDisplayFeelsLikeTemperature(temperature: String): ForecastHeaderPage = apply {
        forecastHeaderScreen {
            feelsLikeTemperature {
                isDisplayed()
                startsWithText(temperature)
            }
        }
    }

    fun shouldDisplayLocation(location: String): ForecastHeaderPage = apply {
        forecastHeaderScreen {
            location {
                isDisplayed()
                hasText(location)
            }
        }
    }
}