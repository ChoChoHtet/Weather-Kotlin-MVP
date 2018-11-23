package com.android.kotlin.weatherkotlin.view

import com.android.kotlin.weatherapp.model.MainWeather

interface CityView {
    fun showMessage(message:String)
    fun showSnackBar()
    fun showProgressBar()
    fun hideProgressBar()
    fun hideRecyclerView()
    fun  showRecyclerView()
    fun onSuccess(mainWeather: MainWeather)
    fun onError(error:Throwable)
    fun showDataInRecycler()
    fun showTextNotFound()
    fun hideTextNotFound()
}