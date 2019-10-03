package me.androidbox.presentation.router

import androidx.fragment.app.FragmentManager
import me.androidbox.presentation.R
import me.androidbox.presentation.forecast.LoadingFragment

class LoadingFragmentRouterImp(private val fragmentManager: FragmentManager) : LoadingFragmentRouter {

    override fun gotoLoadingFragment() {
        fragmentManager.let {
            val fragmentTransaction = it.beginTransaction()
            fragmentTransaction.replace(R.id.forecastActivityContainer, LoadingFragment(), "LoadingFragment")
            fragmentTransaction.commit()
        }
    }
}