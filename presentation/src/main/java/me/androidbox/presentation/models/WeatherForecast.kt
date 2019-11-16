package me.androidbox.presentation.models

import org.parceler.Parcel
import org.parceler.ParcelConstructor

@Parcel(Parcel.Serialization.BEAN)
data class WeatherForecast @ParcelConstructor constructor(
    val forecast: List<Forecast>,
    val cityName: String,
    val timeZone: String,
    val countryCode: String,
    val stateCode: String)
