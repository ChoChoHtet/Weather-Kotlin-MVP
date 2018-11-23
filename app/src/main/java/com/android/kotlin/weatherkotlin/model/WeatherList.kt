package com.android.kotlin.weatherapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class WeatherList(
    @SerializedName("main")
    var currentCondition: CurrentCondition,
    @SerializedName("weather")
    var weather: List<Weather>,
    @SerializedName("dt_txt")
    var dateTime: String
) {
}