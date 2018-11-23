package com.android.kotlin.weatherkotlin.presenter.presenterImp

import android.content.Context
import com.android.kotlin.weatherapp.api.WeatherApiClient
import com.android.kotlin.weatherapp.api.WeatherApiService
import com.android.kotlin.weatherapp.model.MainWeather
import com.android.kotlin.weatherkotlin.CheckConnection
import com.android.kotlin.weatherkotlin.presenter.CityPresenter
import com.android.kotlin.weatherkotlin.view.CityView
import com.android.kotlin.weatherkotlin.view.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CityPresenterImp(private var cityView: CityView?) : CityPresenter {
    private var disposable: CompositeDisposable? = CompositeDisposable()
    private  val weatherApiService: WeatherApiService

    init {
       // disposable = CompositeDisposable()
        weatherApiService = WeatherApiClient.instance.create(WeatherApiService::class.java)

    }

    override fun fetchAPIData(cityName: String) {
        disposable!!.add(weatherApiService.getDataByCity(cityName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            /*.doOnError {
                *//*cityView?.apply {
                    hideProgressBar()
                    hideRecyclerView()
                   // showTextNotFound()
                    onError(it)
                }*//*
            }
            .doOnNext {
                cityView!!.onSuccess(it)
            }*/.subscribe(this::handleResponse,this::handleError)
        )

    }
  private  fun handleResponse(mainWeather: MainWeather){
        cityView!!.onSuccess(mainWeather)
    }
  private  fun handleError(error:Throwable){
        cityView!!.apply {
            hideProgressBar()
            hideRecyclerView()
            showTextNotFound()
            onError(error)
        }

    }

    override fun onDestroy() {
        if (disposable != null) {
            disposable!!.dispose()
            disposable!!.clear()
        }
        cityView = null
    }

    override fun checkInternet(context: Context) {
        when {
            !CheckConnection.checkConnection(context) ->
                cityView!!.apply {
                    hideRecyclerView()
                    hideTextNotFound()
                    showProgressBar()
                    showSnackBar()
                }
            else -> cityView!!.apply {
                hideProgressBar()
                hideTextNotFound()
                showRecyclerView()
                showDataInRecycler()
            }
        }

    }
}