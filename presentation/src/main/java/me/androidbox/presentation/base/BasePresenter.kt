package me.androidbox.presentation.base

interface BasePresenter<V> {
    fun attachView(view: V?)
    fun detachView()
    fun isViewAttached(): Boolean
    fun getView(): V?
}