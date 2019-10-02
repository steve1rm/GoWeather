package me.androidbox.presentation.router

import androidx.fragment.app.FragmentManager

interface ForecastFragmentRouter {
    fun goToForecastFragment(fragmentManager: FragmentManager, latitude: Double, longitude: Double, statusCallback: () -> Unit)
}
