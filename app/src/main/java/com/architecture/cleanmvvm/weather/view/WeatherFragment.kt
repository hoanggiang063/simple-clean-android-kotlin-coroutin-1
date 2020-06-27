package com.architecture.cleanmvvm.weather.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.architecture.cleanmvvm.R
import com.architecture.cleanmvvm.weather.viewmodel.WeatherViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class WeatherFragment : Fragment() {

    private val viewModel: WeatherViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getSetUpView(), container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun getSetUpView(): Int {
        return R.layout.fragment_weather
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadScreenData()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun loadScreenData() {
        TODO("Not yet implemented")
    }

    fun showGenericError(error: String) {
        Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show()
    }

    fun showTechnicalError(error: String) {
        Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show()
    }

}