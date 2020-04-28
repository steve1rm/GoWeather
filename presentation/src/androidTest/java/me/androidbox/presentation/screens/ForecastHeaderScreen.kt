package me.androidbox.presentation.screens

import com.agoda.kakao.common.views.KView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import me.androidbox.presentation.R

class ForecastHeaderScreen : Screen<ForecastHeaderScreen>() {

    val weatherForecastHeader: KView =
        KView { withId(R.id.weather_forecast_header) }

    val temperature: KTextView =
        KTextView { withId(R.id.tvTemperatureDegrees) }

    val feelsLikeTemperature: KTextView =
        KTextView { withId(R.id.tvFeelsLikeTemperatureDegrees) }

    val location: KTextView =
        KTextView { withId(R.id.tvLocationName) }
}
