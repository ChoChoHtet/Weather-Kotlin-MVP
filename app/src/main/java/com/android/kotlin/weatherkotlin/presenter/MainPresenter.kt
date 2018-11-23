package com.android.kotlin.weatherkotlin.presenter

import android.content.Context

interface MainPresenter {
    fun fetchAPIData()
    fun onDestroy()
    fun checkInternet(context: Context)
}