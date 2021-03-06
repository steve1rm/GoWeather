package me.androidbox.presentation.forecast

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.failurecase_layout.*
import me.androidbox.presentation.R

class RetryFragment : Fragment() {

    private var activity: RetryListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as RetryListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.failurecase_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnRetry.setOnClickListener {
            activity?.onRetry()
        }
    }
}

