package me.androidbox.presentation.models

import org.parceler.Parcel
import org.parceler.ParcelConstructor

@Parcel(Parcel.Serialization.BEAN)
data class ForecastDay @ParcelConstructor constructor(
    val date: String,
    val dateEpoch: String,
    val day: Day)