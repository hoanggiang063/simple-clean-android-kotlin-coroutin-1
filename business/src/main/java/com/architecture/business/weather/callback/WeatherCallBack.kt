package com.architecture.cleanmvvm.node1.demo.callback

import com.architecture.business.core.callback.BasePresentCallBack
import com.architecture.business.core.exception.BusinessException
import com.architecture.cleanmvvm.node1.demo.info.WeatherInfo

interface WeatherCallBack<WeatherInfo> : BasePresentCallBack<WeatherInfo> {
    fun onCityNotFound(exception: BusinessException)
}