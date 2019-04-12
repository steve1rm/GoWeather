package me.androidbox.presentation.forecast

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var weatherForecastInteractor: WeatherForecastInteractor

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        weatherForecastInteractor.requestWeatherForecast(ForecastRequestModel(0F, 0F, 5))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: SingleObserver<WeatherForecastModel> {
                override fun onSuccess(weatherForecastModel: WeatherForecastModel) {
                    println(weatherForecastModel)
                }

                override fun onError(e: Throwable) {
                    println(e.message)
                }

                override fun onSubscribe(d: Disposable) {}
            })
    }
}
