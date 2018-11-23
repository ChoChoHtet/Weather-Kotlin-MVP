package com.android.kotlin.weatherapp.api

import com.android.kotlin.weatherapp.model.MainWeather
import com.android.kotlin.weatherapp.model.Weather
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherApiService {
  @Headers("x-api-key:0e37b0f0091c490de207d168f595a78b")
  @GET("data/2.5/forecast?lat=16.871311&lon=96.199379")
  fun getData():Observable<MainWeather>

  @Headers("x-api-key: 0e37b0f0091c490de207d168f595a78b")
  @GET("data/2.5/forecast")
  fun getDataByCity(@Query("q")city_name:String):Observable<MainWeather>

}