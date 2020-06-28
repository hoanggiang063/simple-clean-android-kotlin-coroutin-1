package com.architecture.cleanmvvm.weather.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.architecture.cleanmvvm.R
import com.architecture.cleanmvvm.node1.demo.info.WeatherInfo
import com.architecture.cleanmvvm.weather.viewmodel.WeatherViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class WeatherFragment : Fragment() {

    val searchView: SearchView by lazy {
        view!!.findViewById<SearchView>(R.id.searchView)
    }

    val btnGetWeather: AppCompatButton by lazy {
        view!!.findViewById<AppCompatButton>(R.id.btnGetWeather)
    }

    val recyclerForeCast: RecyclerView by lazy {
        view!!.findViewById<RecyclerView>(R.id.recyclerForeCast)
    }

    private val viewModel: WeatherViewModel by viewModel()

    private lateinit var weatherAdapter: WeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getSetUpView(), container, false)
    }

    private fun getSetUpView(): Int {
        return R.layout.fragment_weather
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        weatherAdapter = WeatherAdapter();
        recyclerForeCast.layoutManager = LinearLayoutManager(context)
        recyclerForeCast.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerForeCast.adapter = weatherAdapter

        val dataObserver = Observer<WeatherInfo> { data ->
            data.foreCastItems?.let {
                weatherAdapter.info.clear();
                weatherAdapter.info.addAll(data.foreCastItems!!)
                weatherAdapter.notifyDataSetChanged();
            }

        }

        val failObserver = Observer<Throwable> { data ->
            showGenericError("Something went wrong or city not found")
            weatherAdapter.info.clear();
            weatherAdapter.notifyDataSetChanged();
        }

        viewModel.currentWeatherInfo.observe(this, dataObserver)
        viewModel.failedException.observe(this, failObserver)
        btnGetWeather.setOnClickListener { loadScreenData() }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun loadScreenData() {
        searchView.query?.let { text ->
            viewModel.loadWeather(text.toString())
        }

    }

    private fun showGenericError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    private fun showTechnicalError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

}