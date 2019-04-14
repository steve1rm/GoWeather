package me.androidbox.presentation

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface BaseDelegate<T> {
    fun getViewType(): Int

    fun isForViewType(items: T, position: Int): Boolean

    fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    fun bindViewHolder(holder: RecyclerView.ViewHolder, position: Int, items: List<T>)
}
