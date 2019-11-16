package me.androidbox.presentation.models

import org.parceler.Parcel
import org.parceler.ParcelConstructor

@Parcel(Parcel.Serialization.BEAN)
data class Weather @ParcelConstructor constructor(
    val icon: String,
    val code: Int,
    val description: String)
