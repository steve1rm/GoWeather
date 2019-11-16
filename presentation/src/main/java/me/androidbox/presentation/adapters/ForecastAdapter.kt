package me.androidbox.presentation.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.androidbox.presentation.models.Forecast
import me.androidbox.presentation.models.ForecastDay
import me.androidbox.presentation.viewholders.ForecastViewHolder

class ForecastAdapter(private var forecastDelegate: BaseDelegate<Forecast>) : RecyclerView.Adapter<ForecastViewHolder>() {

    private val forecastDayList = mutableListOf<Forecast>()

    fun populate(forecastDayList: List<Forecast>) {
        this.forecastDayList.addAll(forecastDayList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        return forecastDelegate.createViewHolder(parent) as ForecastViewHolder
    }

    override fun getItemCount(): Int {
        return forecastDayList.size
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        forecastDelegate.bindViewHolder(holder, position, forecastDayList)
    }
}
