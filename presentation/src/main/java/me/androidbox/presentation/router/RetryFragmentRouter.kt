package me.androidbox.presentation.router

import androidx.fragment.app.FragmentManager

interface RetryFragmentRouter {
    fun gotoRetryFragment(fragmentManager: FragmentManager, retryListener: () -> Unit)
}
