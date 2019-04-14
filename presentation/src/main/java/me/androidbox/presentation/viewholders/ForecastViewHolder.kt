package me.androidbox.presentation.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import me.androidbox.presentation.R

class ForecastViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val tvWeekDay = itemView.findViewById<TextView>(R.id.tvWeekDay)
    val tvAverageTemperature = itemView.findViewById<TextView>(R.id.tvAverageTemperature)
}
