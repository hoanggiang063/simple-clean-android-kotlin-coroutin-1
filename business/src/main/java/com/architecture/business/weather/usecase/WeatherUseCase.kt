package com.architecture.cleanmvvm.node1.demo.usecase

import com.architecture.business.core.usecase.BaseUseCase
import com.architecture.cleanmvvm.node1.demo.callback.WeatherCallBack
import com.architecture.cleanmvvm.node1.demo.info.WeatherInfo

interface WeatherUseCase : BaseUseCase<WeatherRequest, WeatherInfo, WeatherCallBack>

data class WeatherRequest(
    var city: String,
    var numberDays: Int,
    var appId: String,
    var unit: String
)