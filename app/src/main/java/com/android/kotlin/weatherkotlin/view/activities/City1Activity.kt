package com.android.kotlin.weatherkotlin.view.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.kotlin.weatherapp.adapter.WeatherAdapter
import com.android.kotlin.weatherapp.baseviewholder.BaseVH
import com.android.kotlin.weatherapp.model.MainWeather
import com.android.kotlin.weatherapp.model.WeatherList
import com.android.kotlin.weatherkotlin.R
import com.android.kotlin.weatherkotlin.presenter.presenterImp.CityPresenterImp
import com.android.kotlin.weatherkotlin.view.CityView
import com.android.kotlin.weatherkotlin.view.MainView
import kotlinx.android.synthetic.main.activity_city1.*
import kotlinx.android.synthetic.main.activity_main.*

class City1Activity : AppCompatActivity(), CityView, BaseVH.Listener {
    override fun showRecyclerView() {
        city_list.visibility=View.VISIBLE
    }

    override fun showTextNotFound() {
       tv_error.visibility=View.VISIBLE
    }

    override fun hideTextNotFound() {
       tv_error.visibility=View.GONE
    }

    override fun onItemClick(position: Int) {
        showMessage("Position $position is Clicked")
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showDataInRecycler() {
        presenter.fetchAPIData(search_city.text.toString())
    }

    override fun showSnackBar() {
        val snackBar = Snackbar.make(relative, "No Internet Connection !!", Snackbar.LENGTH_INDEFINITE)
            .setAction("Try Again") {
                presenter.checkInternet(it.context)
            }
        snackBar.show()
    }

    override fun showProgressBar() {
        progressbar2.visibility = View.VISIBLE

    }

    override fun hideProgressBar() {
        progressbar2.visibility = View.GONE

    }

    override fun hideRecyclerView() {
       city_list.visibility = View.GONE

    }

    override fun onSuccess(mainWeather: MainWeather) {
        if (mainWeather.weatherList.isEmpty()) {
            Log.i("S-Message", "NO data")
            showMessage("No data")
        } else {
            Log.i("S-Message", mainWeather.weatherList.size.toString())
            adapter.addWeatherList(mainWeather.weatherList)
            adapter.notifyDataSetChanged()
            city_list.adapter = adapter
        }


    }

    override fun onError(error: Throwable) {
        Log.e("Error", error.localizedMessage)
        //showMessage("Something was wrong!!")
        //showTextNotFound()

    }

    private lateinit var adapter: WeatherAdapter
    private val presenter = CityPresenterImp(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city1)
        //initUI
        initRecyclerView()

        adapter = WeatherAdapter()
        adapter.setOnItemClickListener(this)

        btnSearch.setOnClickListener {
            presenter.checkInternet(it.context)
        }
    }

    private fun initRecyclerView() {
        city_list.layoutManager = LinearLayoutManager(this)
        city_list.setHasFixedSize(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
