package me.androidbox.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.weather_forecast_item.view.*
import me.androidbox.models.ForecastDayModel
import me.androidbox.models.WeatherForecastModel
import me.androidbox.presentation.BaseDelegate
import me.androidbox.presentation.R
import me.androidbox.presentation.viewholders.ForecastViewHolder

class ForecastDelegate(private val viewType: Int) : BaseDelegate<ForecastDayModel> {
    override fun getViewType(): Int {
        return viewType
    }

    override fun isForViewType(items: ForecastDayModel, position: Int): Boolean {
        return true
    }

    override fun createViewHolder(parent: ViewGroup): ForecastViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ForecastViewHolder(inflater.inflate(R.layout.weather_forecast_item, parent, false))
    }

    override fun bindViewHolder(holder: RecyclerView.ViewHolder, position: Int, items: List<ForecastDayModel>) {
        if(holder is ForecastViewHolder) {
            holder.run {
                tvWeekDay.text = items[position].date
                tvAverageTemperature.text = items[position].day.averageTemperatureInCelsius.toString()
            }
        }
    }
}