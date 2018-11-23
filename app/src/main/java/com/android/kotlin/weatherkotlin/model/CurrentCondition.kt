package com.android.kotlin.weatherapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CurrentCondition(
    @SerializedName("temp")
    var temp:Double?=null,
    @SerializedName("pressure")
    var pressure:Double?=null,
    @SerializedName("humidity")
    var humidity:Int?=null) {
}