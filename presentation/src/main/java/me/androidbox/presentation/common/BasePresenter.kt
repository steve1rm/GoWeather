package me.androidbox.presentation.common

interface BasePresenter<V> {
    fun attachView(view: V?)
    fun detachView()
    fun isViewAttached(): Boolean
    fun getView(): V?
}