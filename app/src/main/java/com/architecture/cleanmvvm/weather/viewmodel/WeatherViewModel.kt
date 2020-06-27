package com.architecture.cleanmvvm.weather.viewmodel

import androidx.lifecycle.ViewModel
import com.architecture.cleanmvvm.node1.demo.usecase.WeatherUseCase

class WeatherViewModel(val weatherUseCase: WeatherUseCase) : ViewModel() {

    fun loadWeather(searchText: String){

    }
}