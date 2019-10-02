package me.androidbox.presentation.router

import androidx.fragment.app.FragmentManager
import me.androidbox.presentation.R
import me.androidbox.presentation.forecast.LoadingFragment
import javax.inject.Inject

class LoadingFragmentRouterImp @Inject constructor() : LoadingFragmentRouter {

    override fun gotoLoadingFragment(fragmentManager: FragmentManager) {
        fragmentManager.let {
            val fragmentTransaction = it.beginTransaction()
            fragmentTransaction.replace(R.id.forecastActivityContainer, LoadingFragment(), "LoadingFragment")
            fragmentTransaction.commit()
        }
    }
}