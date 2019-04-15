package me.androidbox.presentation.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.androidbox.presentation.models.ForecastDay
import me.androidbox.presentation.viewholders.ForecastViewHolder

class ForecastAdapter(private val forecastDay: List<ForecastDay>,
                      private var forecastDelegate: ForecastDelegate) : RecyclerView.Adapter<ForecastViewHolder>() {

    init {
        forecastDelegate = ForecastDelegate(1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        return forecastDelegate.createViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return forecastDay.size
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        forecastDelegate.bindViewHolder(holder, position, forecastDay)
    }
}
