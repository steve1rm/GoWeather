package me.androidbox.models.response


data class CurrentModel(
    val cityName: String,
    val stateCode: String,
    val feelsLikeTemperature: String,
    val temperature: Float,
    val weather: WeatherModel
)


