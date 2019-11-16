package me.androidbox.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.androidbox.presentation.R
import me.androidbox.presentation.models.Forecast
import me.androidbox.presentation.models.ForecastDay
import me.androidbox.presentation.viewholders.ForecastViewHolder
import java.text.SimpleDateFormat
import java.util.*

class ForecastDelegate(private val viewType: Int) : BaseDelegate<Forecast> {
    override fun getViewType(): Int {
        return viewType
    }

    override fun isForViewType(items: Forecast, position: Int): Boolean {
        return true
    }

    override fun createViewHolder(parent: ViewGroup): ForecastViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ForecastViewHolder(inflater.inflate(R.layout.weather_forecast_item, parent, false))
    }

    override fun bindViewHolder(holder: RecyclerView.ViewHolder, position: Int, items: List<Forecast>) {
        if(holder is ForecastViewHolder) {
            holder.run {
                tvWeekDay.text = getWeekday(items[position].validDate)
                tvAverageTemperature.text = items[position].temp.toString()
            }
        }
    }

    private fun getWeekday(date: String): String {
        val longDate = date.toLong()
        val simpleDateFormat = SimpleDateFormat("EEEE", Locale.getDefault())
        val dateFormat = Date(longDate * 1000)

        return simpleDateFormat.format(dateFormat)
    }
}