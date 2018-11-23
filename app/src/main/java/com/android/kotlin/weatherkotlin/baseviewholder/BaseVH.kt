package com.android.kotlin.weatherapp.baseviewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import java.text.ParsePosition

abstract class BaseVH<in T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(data: T, listener: BaseVH.Listener)

    interface Listener {
        fun onItemClick(position: Int)
    }
}