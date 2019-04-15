package me.androidbox.presentation.models

import org.parceler.Parcel
import org.parceler.ParcelConstructor

@Parcel(Parcel.Serialization.BEAN)
data class Location @ParcelConstructor constructor(
    val name: String,
    val region: String,
    val country: String)
