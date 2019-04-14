package me.androidbox.presentation.forecast

import android.animation.AnimatorInflater
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.inprogress.*
import me.androidbox.models.WeatherForecastModel
import me.androidbox.presentation.R
import javax.inject.Inject

class ForecastFragment : Fragment(), ForecastView {

    @Inject
    lateinit var forecastPresenter: ForecastPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AndroidSupportInjection.inject(this)
        val view = inflater.inflate(R.layout.inprogress, container, false)

        forecastPresenter.initialize(this)
        forecastPresenter.requestWeatherForecast()

        return view
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

    override fun onDestroyView() {
        forecastPresenter.release()
        super.onDestroyView()
    }

    override fun onForecastSuccess(weatherForecastModel: WeatherForecastModel) {
        println("onForecastSuccess ${weatherForecastModel.forecast.forecastDay[0].day.averageTemperatureInCelsius}")
    }

    override fun onForecastFailure(error: String) {
        println("onForecastFailure $error")
    }
}
