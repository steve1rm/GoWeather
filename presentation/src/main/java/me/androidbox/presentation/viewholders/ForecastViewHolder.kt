package me.androidbox.presentation.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import me.androidbox.presentation.R

class ForecastViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val tvWeekDay: TextView = itemView.findViewById(R.id.tvWeekDay)
    val tvAverageTemperature: TextView = itemView.findViewById(R.id.tvAverageTemperature)
    val tvWeatherDescription: TextView = itemView.findViewById(R.id.tvWeatherDescription)
}
