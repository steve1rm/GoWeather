package me.androidbox.presentation.forecast

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import dagger.android.AndroidInjection
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import me.androidbox.interactors.WeatherForecastInteractor
import me.androidbox.models.ForecastRequestModel
import me.androidbox.models.WeatherForecastModel
import me.androidbox.presentation.R
import javax.inject.Inject

class ForecastActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        startFragment()
    }

    private fun startFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.forecastActivityContainer, ForecastFragment(), "ForecastFragment")
        fragmentTransaction.commit()
    }
}
