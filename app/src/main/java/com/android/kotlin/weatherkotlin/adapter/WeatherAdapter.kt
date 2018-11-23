package com.android.kotlin.weatherapp.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.kotlin.weatherapp.baseviewholder.BaseVH
import com.android.kotlin.weatherapp.model.WeatherList
import com.android.kotlin.weatherapp.viewholder.WeatherHolder
import com.android.kotlin.weatherkotlin.R


class WeatherAdapter : RecyclerView.Adapter<WeatherHolder>() {
    private var dataList: List<WeatherList> = ArrayList<WeatherList>()
    lateinit var listener: BaseVH.Listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHolder {
        return WeatherHolder(LayoutInflater.from(parent.context).inflate(R.layout.weather_item, parent, false))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
    fun setOnItemClickListener(listener:BaseVH.Listener){
        this.listener=listener
    }

    override fun onBindViewHolder(holder: WeatherHolder, position: Int) {
        holder.bind(dataList[position],listener)

    }

    fun addWeatherList(list: List<WeatherList>) {
        this.dataList = list

    }
}