package com.android.kotlin.weatherapp.viewholder

import android.view.View
import com.android.kotlin.weatherapp.baseviewholder.BaseVH
import com.android.kotlin.weatherapp.model.WeatherList
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.weather_item.view.*

class WeatherHolder(itemView: View) : BaseVH<WeatherList>(itemView) {
    private val dateTime = itemView.date_time
    private val temp = itemView.temperature
    private val humidity = itemView.humidity
    private val desc = itemView.description
    private val pressure = itemView.pressure
    private val icon = itemView.icon
    override fun bind(data: WeatherList,listener: Listener) {
        dateTime.text = data.dateTime
        desc.text = data.weather[0].description
        temp.text = data.currentCondition.temp.toString()
        humidity.text = data.currentCondition.humidity.toString()
        pressure.text = data.currentCondition.pressure.toString()
        Picasso.with(itemView.context).load("http://openweathermap.org/img/w/${data.weather[0].icon}.png").into(icon)
        itemView.setOnClickListener {
            listener.onItemClick(adapterPosition)
        }

    }
}