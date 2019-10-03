package me.androidbox.presentation.router

interface RetryFragmentRouter {
    fun gotoRetryFragment(retryListener: () -> Unit)
}
