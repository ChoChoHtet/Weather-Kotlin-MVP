package com.android.kotlin.weatherapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MainWeather(
    @SerializedName("list")
    var weatherList: List<WeatherList>){}