package me.androidbox.presentation.forecast


import android.animation.AnimatorInflater
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.inprogress.*
import me.androidbox.presentation.R

class LoadingFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.inprogress, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startLoading()
    }

    private fun startLoading() {
        val rotation = AnimatorInflater.loadAnimator(activity, R.animator.loading_progress)
        rotation.setTarget(ivProgress)
        rotation.start()
    }
}
