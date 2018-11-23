package com.android.kotlin.weatherkotlin.view.activities

import android.content.Intent
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
import com.android.kotlin.weatherkotlin.R
import com.android.kotlin.weatherkotlin.presenter.presenterImp.MainPresenterImp
import com.android.kotlin.weatherkotlin.view.MainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView ,BaseVH.Listener{
    override fun onItemClick(position: Int) {
        showMessage("Position $position is Clicked")
    }

    override fun showMessage(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    override fun showDataInRecycler() {
        presenter.fetchAPIData()
    }

    override fun showSnackBar() {
        val snackBar = Snackbar.make(layout, "No Internet Connection !!", Snackbar.LENGTH_INDEFINITE)
            .setAction("Try Again") {
                presenter.checkInternet(it.context)
        }
        snackBar.show()
    }

    override fun showProgressBar() {
        progressbar.visibility = View.VISIBLE

    }

    override fun hideProgressBar() {
        progressbar.visibility = View.GONE

    }

    override fun hideRecyclerView() {
        weather_list.visibility = View.GONE

    }

    override fun onSuccess(mainWeather: MainWeather) {
        if(mainWeather.weatherList.isEmpty()){
            Log.i("S-Message", "NO data")
            showMessage("No data")
        }else{
            Log.i("S-Message", mainWeather.weatherList.size.toString())
            adapter.addWeatherList(mainWeather.weatherList)
            adapter.notifyDataSetChanged()
            weather_list.adapter=adapter
        }


    }

    override fun onError(error: Throwable) {
        Log.e("Error",error.localizedMessage)
        showMessage("Something was wrong!!")

    }


    private lateinit var adapter: WeatherAdapter
    private val presenter=MainPresenterImp(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //initUI
        initRecyclerView()

        adapter = WeatherAdapter()
        adapter.setOnItemClickListener(this)

        //check connection
        presenter.checkInternet(this)
        btnCity.setOnClickListener {
            it.context.startActivity(Intent(it.context,City1Activity::class.java))
        }

    }

    private fun initRecyclerView() {
        weather_list.layoutManager = LinearLayoutManager(this)
        weather_list.setHasFixedSize(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

}
