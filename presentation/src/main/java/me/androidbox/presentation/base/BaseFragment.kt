package me.androidbox.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import me.androidbox.presentation.di.forecast.ForecastFragmentComponent
import me.androidbox.presentation.di.forecast.ForecastFragmentModule
import me.androidbox.presentation.di.GoWeatherApplication
import me.androidbox.presentation.di.application.GoWeatherApplicationComponent
import javax.inject.Inject

abstract class BaseFragment<VM: BaseViewModel> : Fragment() {

    @Inject
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(buildFragmentComponent())
        super.onCreate(savedInstanceState)
        setupObservers()
        viewModel.onCreate()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(provideLayoutId(), container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupView(view, savedInstanceState)
    }

    protected open fun setupObservers() {
        viewModel.messageStringId.observe(this@BaseFragment, Observer {
            showMessage(it)
        })

        viewModel.messageString.observe(this@BaseFragment, Observer<String> {
                message -> showMessage(message)
        })
    }

    internal fun showMessage(message: String) =
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()

    internal fun showMessage(@StringRes resourceId: Int) =
        showMessage(getString(resourceId))

    @LayoutRes
    protected abstract fun provideLayoutId(): Int

    protected abstract fun setupView(view: View, savedInstanceState: Bundle?)

    protected abstract fun injectDependencies(forecastFragmentComponent: ForecastFragmentComponent)

    private fun buildFragmentComponent(): ForecastFragmentComponent {
        return getGoWeatherApplicationComponent()
            .newForecastFragmentComponent(ForecastFragmentModule(this))
    }

    private fun getGoWeatherApplicationComponent(): GoWeatherApplicationComponent {
        return (activity!!.applicationContext as GoWeatherApplication).goWeatherApplicationComponent
    }
}
