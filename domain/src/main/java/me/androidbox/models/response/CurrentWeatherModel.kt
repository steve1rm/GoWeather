package me.androidbox.models.response


data class CurrentWeatherModel(
    val cityName: String,
    val stateCode: String,
    val feelsLikeTemperature: Float,
    val temperature: Float,
    val weather: WeatherModel
)


