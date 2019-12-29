package me.androidbox.presentation.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import me.androidbox.presentation.R

class ForecastViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val tvWeekDay: TextView = itemView.findViewById(R.id.tvWeekDay)
    val tvHighTemperature: TextView = itemView.findViewById(R.id.tvHighTemperature)
    val tvLowTemperature: TextView = itemView.findViewById(R.id.tvLowTemperature)
    val tvWeatherDescription: TextView = itemView.findViewById(R.id.tvWeatherDescription)
}
