package me.androidbox.presentation.router

import androidx.fragment.app.FragmentManager
import me.androidbox.presentation.R
import me.androidbox.presentation.forecast.RetryFragment

class RetryFragmentRouterImp(private val fragmentManager: FragmentManager) : RetryFragmentRouter {

    override fun gotoRetryFragment(retryListener: () -> Unit) {
        fragmentManager.let {
            val fragmentTransaction = it.beginTransaction()
            fragmentTransaction.replace(R.id.forecastActivityContainer, RetryFragment(retryListener), "RetryFragmentRouter")
            fragmentTransaction.commit()
        }
    }
}
