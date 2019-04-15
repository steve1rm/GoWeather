package me.androidbox.presentation.models

import org.parceler.Parcel
import org.parceler.ParcelConstructor

@Parcel(Parcel.Serialization.BEAN)
data class WeatherForecast @ParcelConstructor constructor(
    val location: Location,
    val current: Current,
    val forecast: Forecast)
