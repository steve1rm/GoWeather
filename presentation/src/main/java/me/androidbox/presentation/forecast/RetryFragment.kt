package me.androidbox.presentation.forecast

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.failurecase_layout.*
import me.androidbox.presentation.R
import me.androidbox.presentation.di.DaggerForecastActivityComponent
import javax.inject.Inject

class RetryFragment(private val retryListener: () -> Unit) : Fragment() {

    @Inject
    lateinit var activity: RetryListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as RetryListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerForecastActivityComponent
            .builder()
            .build()
            .inject(this)

        println("")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.failurecase_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnRetry.setOnClickListener {
            retryListener()
//            activity.onRetry()
        }
    }
}

