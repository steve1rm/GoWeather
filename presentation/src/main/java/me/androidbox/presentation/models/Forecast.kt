package me.androidbox.presentation.models

import org.parceler.Parcel
import org.parceler.ParcelConstructor

@Parcel(Parcel.Serialization.BEAN)
data class Forecast @ParcelConstructor constructor(
    val temp: Float,
    val highTemp: Float,
    val lowTemp: Float,
    val feelsLikeMinTemp: Float,
    val feelsLikeMaxTemp: Float,
    val validDate: String,
    val weather: Weather)
