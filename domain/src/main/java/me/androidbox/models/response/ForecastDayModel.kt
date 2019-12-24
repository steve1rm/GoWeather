package me.androidbox.models.response

import me.androidbox.models.response.DayModel

data class ForecastDayModel(
    val date: String,
    val dateEpoch: String,
    val day: DayModel
)
