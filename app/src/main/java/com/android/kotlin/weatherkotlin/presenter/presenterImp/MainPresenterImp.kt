package com.android.kotlin.weatherkotlin.presenter.presenterImp

import android.content.Context
import com.android.kotlin.weatherapp.api.WeatherApiClient
import com.android.kotlin.weatherapp.api.WeatherApiService
import com.android.kotlin.weatherapp.model.MainWeather
import com.android.kotlin.weatherkotlin.CheckConnection
import com.android.kotlin.weatherkotlin.presenter.MainPresenter
import com.android.kotlin.weatherkotlin.view.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

class MainPresenterImp( private var mainView: MainView?) : MainPresenter {
    private var disposable: CompositeDisposable? = CompositeDisposable()
    private lateinit var weatherApiService: WeatherApiService

   /* init {
        disposable = CompositeDisposable()
        weatherApiService = WeatherApiClient.instance.create(WeatherApiService::class.java)

    }*/

    override fun checkInternet(context: Context) {
        when {
            !CheckConnection.checkConnection(context) ->
                mainView!!.apply {
                    showProgressBar()
                    showSnackBar()
                }
            else -> mainView?.apply {
                mainView!!.apply {
                    hideProgressBar()
                    showDataInRecycler()
                }
            }
        }
    }

    override fun fetchAPIData() {
        weatherApiService = WeatherApiClient.instance.create(WeatherApiService::class.java)
        disposable!!.add(
            weatherApiService.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                 .doOnError {
                     mainView!!.onError(it)
                 }
                 .doOnNext {
                     mainView!!.onSuccess(it)
                 }.subscribe()
        )

    }


    override fun onDestroy() {
        if (disposable != null) {
            disposable!!.dispose()
            disposable!!.clear()
        }
        mainView = null

    }


}