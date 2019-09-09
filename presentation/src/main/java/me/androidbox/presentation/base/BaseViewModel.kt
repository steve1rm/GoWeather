package me.androidbox.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import me.androidbox.presentation.utils.NetworkHelper

abstract class BaseViewModel(
    protected val compositeDisposable: CompositeDisposable,
    protected val networkHelper: NetworkHelper)
    : ViewModel() {

    internal val messageStringId = MutableLiveData<Int>()
    internal val messageString = MutableLiveData<String>()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    protected fun handleNetworkError(error: Throwable) {
        TODO("handle network error")
    }

    protected fun checkInternetConnection(): Boolean =
        networkHelper.isNetworkConnected()

    abstract fun onCreate()
}
