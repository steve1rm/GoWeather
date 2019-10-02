package me.androidbox.presentation.router

import androidx.fragment.app.FragmentManager
import me.androidbox.presentation.R
import me.androidbox.presentation.di.scopes.ActivityScope
import me.androidbox.presentation.forecast.RetryFragment
import javax.inject.Inject

@ActivityScope
class RetryFragmentRouterImp @Inject constructor() : RetryFragmentRouter {

    override fun gotoRetryFragment(fragmentManager: FragmentManager, retryListener: () -> Unit) {
        fragmentManager.let {
            val fragmentTransaction = it.beginTransaction()
            fragmentTransaction.replace(R.id.forecastActivityContainer, RetryFragment(retryListener), "RetryFragmentRouter")
            fragmentTransaction.commit()
        }
    }
}
