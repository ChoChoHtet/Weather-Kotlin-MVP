package com.android.kotlin.weatherkotlin.presenter

import android.content.Context

interface CityPresenter {
    fun fetchAPIData(cityName:String)
    fun onDestroy()
    fun checkInternet(context: Context)
}