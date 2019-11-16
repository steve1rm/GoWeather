package me.androidbox.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.androidbox.presentation.R
import me.androidbox.presentation.models.Forecast
import me.androidbox.presentation.viewholders.ForecastViewHolder
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter


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
                val temperature = "${items[position].temp}\u00B0"

                tvWeekDay.text = getWeekday(items[position].validDate)
                tvAverageTemperature.text = temperature
                tvWeatherDescription.text = items[position].weather.description
            }
        }
    }

    private fun getWeekday(date: String): String {
        /* Create date object from string and format it to 'Sunday 17' */
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val localDate = LocalDate.parse(date, formatter)
        val dayFormatter = DateTimeFormatter.ofPattern("EEEE dd")

        return localDate.format(dayFormatter)
    }
}