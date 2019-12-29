package me.androidbox.presentation.models

import org.parceler.Parcel
import org.parceler.ParcelConstructor

@Parcel(Parcel.Serialization.BEAN)
data class CurrentWeather @ParcelConstructor constructor(
    val cityName: String,
    val stateCode: String,
    val feelsLikeTemperature: Float,
    val temperature: Float,
    val weather: Weather)
