package me.androidbox.presentation.common

import java.lang.ref.WeakReference

class BasePresenterImp<V> : BasePresenter<V> {
    private var viewReference: WeakReference<V?>? = null

    override fun attachView(view: V?) {
       viewReference = WeakReference(view)
    }

    override fun detachView() {
        viewReference?.clear()
        viewReference = null
    }

    override fun isViewAttached(): Boolean {
        return viewReference != null
    }

    override fun getView(): V? {
        return if(isViewAttached()) {
            viewReference?.get()
        }
        else {
            null
        }
    }
}
