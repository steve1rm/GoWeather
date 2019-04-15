package me.androidbox.presentation.models

import org.parceler.Parcel
import org.parceler.ParcelConstructor

@Parcel(Parcel.Serialization.BEAN)
data class Day @ParcelConstructor constructor(
    val averageTemperatureInCelsius: Float)