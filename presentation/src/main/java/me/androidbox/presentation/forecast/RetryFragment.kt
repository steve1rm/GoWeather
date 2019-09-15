package me.androidbox.presentation.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.failurecase_layout.*
import me.androidbox.presentation.R

class RetryFragment(private val retryListener: () -> Unit) : Fragment() {

   // @Inject
   // lateinit var activity: RetryListener

 /*   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerForecastFragmentComponent
            .builder()
            .goWeatherComponent((context?.applicationContext as GoWeatherApplication).component)
            .build()
            .inject(this)
    }*/

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

