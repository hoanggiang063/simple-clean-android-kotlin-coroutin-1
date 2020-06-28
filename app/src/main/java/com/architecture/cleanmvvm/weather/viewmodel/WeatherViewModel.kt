package com.architecture.cleanmvvm.weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.architecture.cleanmvvm.node1.demo.callback.WeatherCallBack
import com.architecture.cleanmvvm.node1.demo.info.WeatherInfo
import com.architecture.cleanmvvm.node1.demo.usecase.WeatherRequest
import com.architecture.cleanmvvm.node1.demo.usecase.WeatherUseCase


class WeatherViewModel(val weatherUseCase: WeatherUseCase) : ViewModel() {

    private val _currentWeatherInfo = MutableLiveData<WeatherInfo>()
    var currentWeatherInfo: LiveData<WeatherInfo> = _currentWeatherInfo

    //lateinit var currentWeatherInfo: WeatherInfo
    private val _failedException = MutableLiveData<Throwable>()
    var failedException: LiveData<Throwable> = _failedException

    fun loadWeather(searchText: String) {

        val request: WeatherRequest = WeatherRequest();
        request.city = searchText;
        request.appId = "60c6fbeb4b93ac653c492ba806fc346d"
        request.numberDays = 7;
        weatherUseCase
            .buildUseCase(request)
            .invoke(object : WeatherCallBack {
                override fun onSuccess(expectedResult: WeatherInfo?) {
                    _currentWeatherInfo.value = expectedResult
                }

                override fun onFail(throwable: Throwable) {
                    _failedException.value = throwable
                }

            })
    }
}