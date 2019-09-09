package me.androidbox.presentation.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import me.androidbox.presentation.di.DaggerForecastActivityComponent
import me.androidbox.presentation.di.ForecastActivityComponent
import me.androidbox.presentation.di.GoWeatherApplication
import javax.inject.Inject

abstract class BaseActivity<VM: BaseViewModel> : AppCompatActivity() {

    @Inject
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies(buildActivityComponent())
        super.onCreate(savedInstanceState)
        setContentView(provideLayoutId())
        setupObservers()
        setupView(savedInstanceState)
        viewModel.onCreate()
    }

    protected open fun setupObservers() {
        viewModel.messageStringId.observe(this@BaseActivity, Observer<Int> { messageId ->
            showMessageId(messageId)
        })

        viewModel.messageString.observe(this@BaseActivity, Observer { message ->
            showMessage(message)
        })
    }

    private fun buildActivityComponent(): ForecastActivityComponent {
        return DaggerForecastActivityComponent
            .builder()
            .goWeatherComponent((application as GoWeatherApplication).component)
            .build()
    }

    internal fun showMessage(message: String) =
        Toast.makeText(this@BaseActivity, message, Toast.LENGTH_SHORT).show()

    internal fun showMessageId(@StringRes resourceId: Int) =
        showMessage(getString(resourceId))

    @LayoutRes
    protected abstract fun provideLayoutId(): Int

    protected abstract fun setupView(savedInstanceState: Bundle?)

    protected abstract fun injectDependencies(forecastActivityComponent: ForecastActivityComponent)
}
