package me.androidbox.models.response

data class ForecastModel(
    val temp: Float,
    val highTemp: Float,
    val lowTemp: Float,
    val feelsLikeMinTemp: Float,
    val feelsLikeMaxTemp: Float,
    val validDate: String,
    val weather: WeatherModel
)
