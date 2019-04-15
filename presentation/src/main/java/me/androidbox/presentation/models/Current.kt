package me.androidbox.presentation.models

import org.parceler.Parcel
import org.parceler.ParcelConstructor

@Parcel(Parcel.Serialization.BEAN)
data class Current @ParcelConstructor constructor(
    val temperatureInCelsius: Int)